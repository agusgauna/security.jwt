package ar.com.ada.sb.security.jwt.model.entity.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String userName;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToMany
    @JoinTable(name = "User_has_Authority",
    joinColumns = {@JoinColumn(name = "User_id", referencedColumnName = "id") },
    inverseJoinColumns = {@JoinColumn(name = "Authority_id", referencedColumnName = "id") })
    private Set<Authority> authorities;

}
