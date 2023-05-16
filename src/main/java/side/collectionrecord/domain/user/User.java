package side.collectionrecord.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @Column
    private String profile;

    @Builder
    public User(String username, String email, String password, String profile){
        this.username = username;
        this.email = email;
        this.password = password;
        this.profile = profile;
    }

    public void update(String username, String profile){
        this.username = username;
        this.profile = profile;
    }
}
