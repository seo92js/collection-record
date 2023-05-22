package side.collectionrecord.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.BaseTimeEntity;
import side.collectionrecord.domain.category.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column
    private String image;

    @OneToMany(mappedBy = "user")
    private List<Category> categories = new ArrayList<>();

    @Builder
    public User(String username, String password, String image){
        this.username = username;
        this.password = password;
        this.image = image;
    }

    public void update(String username, String image){
        this.username = username;
        this.image = image;
    }
}
