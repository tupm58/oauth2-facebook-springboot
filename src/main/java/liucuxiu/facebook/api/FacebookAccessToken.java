package liucuxiu.facebook.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by MinhTu on 12/27/2016.
 */
public class FacebookAccessToken {
    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty(value = "expires_in")
    private int expiresIn; // seconds token expires

    @JsonProperty(value = "token_type")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
