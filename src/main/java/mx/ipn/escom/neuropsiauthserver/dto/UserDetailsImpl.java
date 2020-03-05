package mx.ipn.escom.neuropsiauthserver.dto;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Builder;
import mx.ipn.escom.neuropsiauthserver.entity.User;

@Builder
public class UserDetailsImpl implements UserDetails {

  private static final long serialVersionUID = 1L;
  private User user;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    System.out.println(user.getRoles());
    return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName()))
        .collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return !user.isExpiredAccount();
  }

  @Override
  public boolean isAccountNonLocked() {
    return !user.isLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return !user.isExpiredCredentials();
  }

  @Override
  public boolean isEnabled() {
    return user.isEnabled();
  }

}
