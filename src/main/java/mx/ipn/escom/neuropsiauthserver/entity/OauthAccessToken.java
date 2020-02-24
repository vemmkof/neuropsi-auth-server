package mx.ipn.escom.neuropsiauthserver.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@Column(name = "token_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tokenId;

	private String token;
}
