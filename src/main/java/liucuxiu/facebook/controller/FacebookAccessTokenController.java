package liucuxiu.facebook.controller;

import liucuxiu.facebook.dto.AccessTokenDTO;
import liucuxiu.facebook.dto.AccessTokenValidationResultDTO;
import liucuxiu.facebook.service.impl.FacebookAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MinhTu on 12/26/2016.
 */
@RestController
@RequestMapping(value = "/oauth/facebook/access-token")
public class FacebookAccessTokenController {

    @Autowired
    private FacebookAccessTokenService tokenService;

    @PostMapping("/exchange")
    public AccessTokenValidationResultDTO exchange(@RequestBody AccessTokenDTO accessTokenDTO) {
        return tokenService.exchange(accessTokenDTO);
    }
}
