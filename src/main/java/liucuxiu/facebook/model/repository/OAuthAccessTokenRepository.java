package liucuxiu.facebook.model.repository;

import liucuxiu.facebook.model.entity.OAuthAccessToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MinhTu on 12/27/2016.
 */
@Repository
public interface OAuthAccessTokenRepository extends CrudRepository<OAuthAccessToken,String>{
}
