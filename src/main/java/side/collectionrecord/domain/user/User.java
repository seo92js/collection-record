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
    private String profile_image;

    @Builder
    public User(String username, String email, String password, String profile_image){
        this.username = username;
        this.email = email;
        this.password = password;
        this.profile_image = profile_image;
    }
}
