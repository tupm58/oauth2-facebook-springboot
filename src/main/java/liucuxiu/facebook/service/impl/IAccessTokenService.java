package liucuxiu.facebook.service.impl;

import liucuxiu.facebook.dto.AccessTokenDTO;
import liucuxiu.facebook.dto.AccessTokenValidationResultDTO;

import java.io.IOException;

/**
 * Created by MinhTu on 12/26/2016.
 */
public interface IAccessTokenService {
    AccessTokenValidationResultDTO exchange(AccessTokenDTO accessToken) throws IOException;

}
