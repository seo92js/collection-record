package side.collectionrecord.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.BaseTimeEntity;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.comment.Comment;
import side.collectionrecord.domain.follow.Follow;

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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "following", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Follow> following = new ArrayList<>();
    @OneToMany(mappedBy = "follower", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Follow> follower = new ArrayList<>();

    public void addCategory(Category category){
        this.categories.add(category);
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void following(User user, Follow follow){
        this.following.add(follow);
        user.follower.add(follow);
    }

    public void unfollowing(User user, Follow follow){
        this.following.remove(follow);
        user.follower.remove(follow);
    }

    public Follow findFollowByUser(User user){
        for (Follow follow : following) {
            if (follow.getFollower().equals(user)){
                return follow;
            }
        }
        return null;
    }

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
