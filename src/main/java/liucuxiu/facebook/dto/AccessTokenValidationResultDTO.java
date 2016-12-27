package liucuxiu.facebook.dto;

/**
 * Created by MinhTu on 12/26/2016.
 */
public class AccessTokenValidationResultDTO {
//    private TokenValidationStatus validationStatus;
    private AccessTokenDTO accessToken;

    public AccessTokenDTO getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessTokenDTO accessToken) {
        this.accessToken = accessToken;
    }
}
