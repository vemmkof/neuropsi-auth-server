package mx.ipn.escom.neuropsiauthserver.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import mx.ipn.escom.neuropsiauthserver.entity.values.Gender;
import mx.ipn.escom.neuropsiauthserver.entity.values.Role;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements Serializable {

  private static final long serialVersionUID = 5084202603438255147L;

  @Id
  @Column(name = "id_user", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Positive
  private Long idUser;

  @Email
  @NotBlank
  @Column(name = "username", nullable = false)
  private String username;

  @NotEmpty
  @Min(value = 8)
  @JsonProperty(access = Access.WRITE_ONLY)
  @Column(name = "password", nullable = false)
  private String password;

  @NotEmpty
  @Column(name = "name", nullable = false)
  private String name;

  @NotEmpty
  @Column(name = "lastname", nullable = false)
  private String lastname;

  @Column(name = "second_lastname", nullable = true)
  private String secondLastname;

  @Past
  @Column(name = "date_of_birth", nullable = false)
  private Date dateOfBirth;

  @NotEmpty
  @Column(name = "phone", nullable = false)
  private String phone;

  @NotEmpty
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Gender gender;

  @NotEmpty
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  @Builder.Default
  @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
  private boolean expiredAccount = false;

  @Builder.Default
  @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
  private boolean lockedAccount = false;

  @Builder.Default
  @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
  private boolean expiredCredentials = false;

  @Builder.Default
  @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
  private boolean enabled = false;

  @CreationTimestamp
  @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private Timestamp created;

  @UpdateTimestamp
  private Timestamp updated;

}

