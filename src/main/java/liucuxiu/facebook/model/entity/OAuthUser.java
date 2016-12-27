package liucuxiu.facebook.model.entity;

import liucuxiu.facebook.enums.OAuthProvider;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by MinhTu on 12/27/2016.
 */
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"provider", "providerUserId"}, name = "oauthuser_provider_provideruserid_unq")
}
)
public class OAuthUser implements Serializable {

    private static final long serialVersionUID = 2352161888620512874L;

    @Id
    @Column(nullable = false)
    private String providerUserId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OAuthProvider provider;

    @Column(nullable = false)
    private String name;

    private String email;
    private String profileUrl;
    private String avatarUrl;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @PrePersist
    @PreUpdate
    public void onUpdate() {
        if (createdDate == null) {
            createdDate = new Date();
        }
        updatedDate = new Date();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public OAuthProvider getProvider() {
        return provider;
    }

    public void setProvider(OAuthProvider provider) {
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
