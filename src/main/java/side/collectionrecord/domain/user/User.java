package side.collectionrecord.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @Column
    private String profileImage;

    @Builder
    public User(String username, String email, String password, String profileImage){
        this.username = username;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
    }
}
