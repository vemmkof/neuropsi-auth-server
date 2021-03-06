package mx.ipn.escom.neuropsiauthserver.config;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  private static final Logger log = LoggerFactory.getLogger(ResourceServerConfig.class);
  @Value("${spring.profiles.active}")
  private String profile;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    log.info("SETTING UP HttpSecurity ");
    if (profile != null && profile.equals("dev")) {
      http//
          .authorizeRequests()//
          .antMatchers(HttpMethod.GET, "/").permitAll() //
          .antMatchers(HttpMethod.GET, "/csrf").permitAll() //
          .antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll() //
          .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll() //
          .antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll() //
          .antMatchers(HttpMethod.GET, "/webjars/springfox-swagger-ui/**").permitAll() //
          .antMatchers(HttpMethod.POST, "/oauth/**").permitAll() //
          .anyRequest().authenticated() //
          .and().cors().configurationSource(getConfigurationSource());
    } else {
      http//
          .authorizeRequests()//
          .antMatchers(HttpMethod.POST, "/oauth/**").permitAll() //
          .anyRequest().authenticated() //
          .and().cors().configurationSource(getConfigurationSource());
    }

  }

  @Bean
  public CorsConfigurationSource getConfigurationSource() {
    log.info("SETTING UP CorsConfigurationSource");
    UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource;
    urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowCredentials(true);
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowedOrigins(Arrays.asList("*")); // dev && prod url
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);
    return urlBasedCorsConfigurationSource;
  }

}
