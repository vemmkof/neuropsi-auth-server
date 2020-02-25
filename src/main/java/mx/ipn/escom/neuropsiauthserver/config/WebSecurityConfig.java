package mx.ipn.escom.neuropsiauthserver.config;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

  @Value("${spring.security.jwt.signing-key}")
  private String signingKey;

  @Autowired
  private DataSource dataSource;

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  @Autowired
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    log.info("SETTING UP AuthenticationManagerBuilder");
    auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    log.info("SETTING UP authenticationManagerBean");
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().anyRequest().authenticated().and().csrf().disable().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    log.info("SETTING UP PasswordEncoder");
    return new BCryptPasswordEncoder();
  }

  @Bean
  public JwtAccessTokenConverter getJwtAccessTokenConverter() {
    log.info("SETTING UP JwtAccessTokenConverter");
    JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
    jwtAccessTokenConverter.setSigningKey(signingKey);
    return jwtAccessTokenConverter;
  }

  @Bean
  public TokenStore getTokenStore() {
    log.info("SETTING UP TokenStore");
    return new JdbcTokenStore(dataSource);
  }

  @Bean
  @Primary
  public DefaultTokenServices getDefaultTokenServices() {
    log.info("SETTING UP DefaultTokenServices");
    DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
    defaultTokenServices.setTokenStore(getTokenStore());
    defaultTokenServices.setSupportRefreshToken(true);
    return defaultTokenServices;
  }

}
