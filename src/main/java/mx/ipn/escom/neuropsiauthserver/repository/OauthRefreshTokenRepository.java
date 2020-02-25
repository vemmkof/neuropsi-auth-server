package mx.ipn.escom.neuropsiauthserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.ipn.escom.neuropsiauthserver.entity.OauthRefreshToken;

@Repository
public interface OauthRefreshTokenRepository extends JpaRepository<OauthRefreshToken, String> {

}
