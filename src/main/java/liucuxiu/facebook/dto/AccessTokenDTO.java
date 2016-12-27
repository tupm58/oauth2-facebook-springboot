package liucuxiu.facebook.dto;

/**
 * Created by MinhTu on 12/26/2016.
 */
public class AccessTokenDTO {
    private String token;
    private OAuthUserDTO user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public OAuthUserDTO getUser() {
        return user;
    }

    public void setUser(OAuthUserDTO user) {
        this.user = user;
    }
}
