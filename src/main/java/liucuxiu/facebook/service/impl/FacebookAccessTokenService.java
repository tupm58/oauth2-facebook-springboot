package liucuxiu.facebook.service.impl;

import liucuxiu.facebook.api.FacebookAccessToken;
import liucuxiu.facebook.api.FacebookUser;
import liucuxiu.facebook.constant.FacebookAPIConstant;
import liucuxiu.facebook.dto.AccessTokenDTO;
import liucuxiu.facebook.dto.AccessTokenValidationResultDTO;
import liucuxiu.facebook.dto.OAuthUserDTO;
import liucuxiu.facebook.enums.OAuthProvider;
import liucuxiu.facebook.model.entity.OAuthAccessToken;
import liucuxiu.facebook.model.entity.OAuthUser;
import liucuxiu.facebook.model.repository.OAuthAccessTokenRepository;
import liucuxiu.facebook.model.repository.OAuthUserRepository;
import ma.glasnost.orika.MapperFacade;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MinhTu on 12/26/2016.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FacebookAccessTokenService implements IAccessTokenService {

    @Value("${social.facebook.app-id}")
    private String appId;
    @Value("${social.facebook.app-secret}")
    private String appSecret;

    @Autowired
    private MapperFacade mapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OAuthUserRepository oAuthUserRepository;
    @Autowired
    private OAuthAccessTokenRepository oAuthAccessTokenRepository;

    public AccessTokenValidationResultDTO exchange(AccessTokenDTO shortLivedToken) {
        //exchange for long-lived toke;
        FacebookAccessToken longLivedToken = exchangeForLongLivedToken(shortLivedToken);
        //fetch user information
        FacebookUser facebookUser = fetchUserProfile(longLivedToken.getAccessToken());
        //save user to db
        OAuthUser user = saveUser(facebookUser);
        //save long-live token
        OAuthAccessToken accessToken = saveAccessToken(longLivedToken, user);
        return generateResponseAccessToken(accessToken, user);
    }

    private AccessTokenValidationResultDTO generateResponseAccessToken(OAuthAccessToken accessToken, OAuthUser user) {
        AccessTokenDTO responseToken = new AccessTokenDTO();
        responseToken.setToken(accessToken.getAccessToken());
        responseToken.setUser(mapper.map(user, OAuthUserDTO.class));

        AccessTokenValidationResultDTO validationResult = new AccessTokenValidationResultDTO();
//        validationResult.setValidationStatus(TokenValidationStatus.VALID);
        validationResult.setAccessToken(responseToken);

        return validationResult;
    }

    private OAuthAccessToken saveAccessToken(FacebookAccessToken longLivedToken, OAuthUser user) {
        OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
        oAuthAccessToken.setAccessToken(longLivedToken.getAccessToken());
        Date expiredDate = DateTime.now().plusSeconds(longLivedToken.getExpiresIn()).toDate();
        oAuthAccessToken.setExpiredDate(expiredDate);
        oAuthAccessToken.setUser(user);
        oAuthAccessTokenRepository.save(oAuthAccessToken);
        return oAuthAccessToken;
    }

    private OAuthUser saveUser(FacebookUser facebookUser) {
        OAuthUser user = new OAuthUser();
        user.setProvider(OAuthProvider.FACEBOOK);
        user.setProviderUserId(facebookUser.getId());
        user.setName(facebookUser.getName());
        user.setEmail(facebookUser.getEmail());
        user.setProfileUrl(facebookUser.getLink());
        user.setAvatarUrl(facebookUser.getPicture().getData().getUrl());
        oAuthUserRepository.save(user);
        return user;
    }

    private FacebookUser fetchUserProfile(String accessToken) {
        Map<String, String> params = new HashMap<>();
        params.put("fields", "id,name,email,link,picture{url}");
        params.put("access_token", accessToken);

        return restTemplate.getForObject(
                FacebookAPIConstant.FB_USER_PROFILE_URL_TEMPLATE,
                FacebookUser.class,
                params
        );
    }

    private FacebookAccessToken exchangeForLongLivedToken(AccessTokenDTO shortLivedToken) {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", appId);
        params.put("client_secret", appSecret);
        params.put("fb_exchange_token", shortLivedToken.getToken());

        return restTemplate.getForObject(
                FacebookAPIConstant.FB_EXCHANGE_TOKEN_URL_TEMPLATE,
                FacebookAccessToken.class,
                params
        );
    }
}
