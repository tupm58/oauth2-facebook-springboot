package liucuxiu.facebook.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by MinhTu on 12/27/2016.
 */
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"accessToken"}, name = "oauthaccesstoken_accesstoken_unq")
}
)
public class OAuthAccessToken implements Serializable {

    private static final long serialVersionUID = 5033992768681267034L;

    @Id
    @Column(nullable = false)
    private String accessToken;

    private String refreshToken;

    @Column(nullable = false)
    private Date expiredDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provider_user_id")
    private OAuthUser user;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public OAuthUser getUser() {
        return user;
    }

    public void setUser(OAuthUser user) {
        this.user = user;
    }
}
