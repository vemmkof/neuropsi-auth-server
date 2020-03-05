package mx.ipn.escom.neuropsiauthserver.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id_user")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idUser;

  @Email
  @NotEmpty(message = "can't be empty")
  @Column(name = "username", nullable = false)
  private String username;

  @JsonProperty(access = Access.WRITE_ONLY)
  @NotEmpty(message = "can't be empty")
  @Column(name = "password", nullable = false)
  private String password;

  @NotNull
  @NotEmpty
  private String name;

  @NotNull
  @NotEmpty
  private String lastname;

  @NotNull
  @NotEmpty
  private String secondLastname;

  @NotNull
  @NotEmpty
  private Date dateOfBirth;

  @NotNull
  @NotEmpty
  private String phone;

  @NotNull
  @NotEmpty
  private boolean genre;

  @JsonIgnore
  @Column(name = "expired_account", nullable = false)
  private boolean expiredAccount = false;

  @JsonIgnore
  @Column(name = "locked_account", nullable = false)
  private boolean locked = false;

  @JsonIgnore
  @Column(name = "expired_credentials", nullable = false)
  private boolean expiredCredentials = false;

  @JsonIgnore
  @Column(name = "enabled", nullable = false)
  private boolean enabled = true;

  @CreationTimestamp
  private Timestamp created;

  @UpdateTimestamp
  private Timestamp updated;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "user_role", //
      joinColumns = {@JoinColumn(referencedColumnName = "id_user")}, //
      inverseJoinColumns = {@JoinColumn(referencedColumnName = "id_role")}//
  )
  private List<Role> roles;
}
