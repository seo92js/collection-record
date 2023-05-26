package side.collectionrecord.domain.category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Posts> posts = new ArrayList<>();

    //연관관계 편의
    public void setUser(User user){
        this.user = user;
        user.addCategory(this);
    }

    @Builder
    public Category(User user, String name){
        setUser(user);
        this.name = name;
    }

    public void update(String name){
        this.name = name;
    }

    public void addPosts(Posts posts){
        this.posts.add(posts);
    }
}
