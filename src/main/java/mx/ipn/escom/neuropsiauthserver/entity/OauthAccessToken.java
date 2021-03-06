package mx.ipn.escom.neuropsiauthserver.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OauthAccessToken implements Serializable {

  private static final long serialVersionUID = -715587613930346354L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String authenticationId;

  @Column(nullable = false)
  private String tokenId;

  @Lob
  @JsonIgnore
  @Column(nullable = false, length = 254 * 4)
  private byte[] token;

  @Column(nullable = false)
  private String userName;

  @Column(nullable = false)
  private String clientId;

  @Lob
  @JsonIgnore
  @Column(nullable = false, length = 254 * 4)
  private byte[] authentication;

  @Column(nullable = false)
  private String refreshToken;

  @CreationTimestamp
  @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private Timestamp created;

  @UpdateTimestamp
  private Timestamp updated;

}
