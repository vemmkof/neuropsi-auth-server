package mx.ipn.escom.neuropsiauthserver.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import mx.ipn.escom.neuropsiauthserver.dto.UserDetailsImpl;
import mx.ipn.escom.neuropsiauthserver.entity.User;
import mx.ipn.escom.neuropsiauthserver.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    Optional<User> optional = userRepository.findByUsername(username);
    if (optional.isPresent()) {
      return UserDetailsImpl.builder().user(optional.get()).build();
    } else {
      throw new UsernameNotFoundException("User not found");
    }
  }

}
