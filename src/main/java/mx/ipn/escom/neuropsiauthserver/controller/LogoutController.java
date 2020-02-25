package mx.ipn.escom.neuropsiauthserver.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.ipn.escom.neuropsiauthserver.service.SessionService;

@Validated
@RestController
@RequestMapping("/oauth/logout")
public class LogoutController {

  @Autowired
  private SessionService sessionService;

  @DeleteMapping
  public ResponseEntity<Boolean> logout(@Validated Principal principal) {
    System.out.println(principal);
    boolean isLoggedOut = sessionService.logout(principal.getName());
    return new ResponseEntity<Boolean>(new Boolean(isLoggedOut), HttpStatus.OK);
  }
}
