package mx.ipn.escom.neuropsiauthserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


  private static final Logger log = LoggerFactory.getLogger(AuthorizationServerConfig.class);


  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AccessTokenConverter accessTokenConverter;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenStore tokenStore;

  @Value("${spring.security.oauth.client-id}")
  private String clientId;

  @Value("${spring.security.oauth.client-secret}")
  private String clientSecret;

  @Value("${spring.security.oauth.access-token-validity-seconds}")
  private int accessTokenValiditySeconds;

  @Value("${spring.security.oauth.refresh-token-validity-seconds}")
  private int refreshTokenValiditySeconds;

  @Value("#{'${spring.security.oauth.scopes}'.split(',')}")
  private String[] scopes;

  @Value("#{'${spring.security.oauth.grant-types}'.split(',')}")
  private String[] grantTypes;

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    log.info("SETTING UP ClientDetailsServiceConfigurer");
    clients.inMemory() //
        .withClient(clientId) //
        .secret(passwordEncoder.encode(clientSecret)) //
        .accessTokenValiditySeconds(accessTokenValiditySeconds) //
        .refreshTokenValiditySeconds(refreshTokenValiditySeconds) //
        .scopes(scopes) //
        .authorizedGrantTypes(grantTypes);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    log.info("SETTING UP AuthorizationServerEndpointsConfigurer");
    endpoints.accessTokenConverter(accessTokenConverter).tokenStore(tokenStore)
        .reuseRefreshTokens(false).authenticationManager(authenticationManager);
  }

}
