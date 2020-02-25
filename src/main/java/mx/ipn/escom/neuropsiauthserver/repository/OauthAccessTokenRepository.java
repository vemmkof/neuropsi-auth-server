package mx.ipn.escom.neuropsiauthserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.ipn.escom.neuropsiauthserver.entity.OauthAccessToken;

@Repository
public interface OauthAccessTokenRepository extends JpaRepository<OauthAccessToken, String> {

	Optional<OauthAccessToken> findByUserName(String userName);

}
