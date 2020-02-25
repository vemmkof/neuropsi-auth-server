package mx.ipn.escom.neuropsiauthserver.entity;

import java.io.Serializable;
import java.sql.Blob;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "oauth_access_token")
public class OauthAccessToken implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private String authenticationId;

  private String tokenId;

  private Blob token;

  private String userName;

  private String clientId;

  private Blob authentication;

  private String refreshToken;

}
