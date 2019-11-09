package bookstore.model;

import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@EnableJpaRepositories
@Entity
@Table(name = "usr")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Username can not be empty!")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Password can not be empty!")
    @Column(name = "password")
    private String password;

    @Transient
    private String passwordConfirmation;

    @Column(name = "active")
    private boolean active;

    @NotBlank(message = "Email can not be empty!")
    @Column(name = "mail")
    private String mail;
}
