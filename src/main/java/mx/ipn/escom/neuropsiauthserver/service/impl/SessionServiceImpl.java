package mx.ipn.escom.neuropsiauthserver.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.ipn.escom.neuropsiauthserver.entity.OauthAccessToken;
import mx.ipn.escom.neuropsiauthserver.repository.OauthAccessTokenRepository;
import mx.ipn.escom.neuropsiauthserver.repository.OauthRefreshTokenRepository;
import mx.ipn.escom.neuropsiauthserver.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService {
	@Autowired
	private OauthAccessTokenRepository oauthAccessTokenRepository;

	@Autowired
	private OauthRefreshTokenRepository oauthRefreshTokenRepository;

	@Override
	public boolean logout(String username) {
		Optional<OauthAccessToken> accessToken = oauthAccessTokenRepository.findByUserName(username);
		if (accessToken.isPresent()) {
			String refreshToken = accessToken.get().getRefreshToken();
			oauthRefreshTokenRepository.deleteById(refreshToken);
			oauthAccessTokenRepository.delete(accessToken.get());
			return true;
		} else {
			return false;
		}
	}

}
