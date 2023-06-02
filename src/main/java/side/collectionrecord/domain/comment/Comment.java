package side.collectionrecord.domain.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.BaseTimeEntity;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id")
    private Posts posts;

    public void setUser(User user){
        this.user = user;
        user.addComment(this);
    }

    public void setPosts(Posts posts){
        this.posts = posts;
        posts.addComment(this);
    }

    @Builder
    public Comment(User user, Posts posts, String text){
        setUser(user);
        setPosts(posts);
        this.text = text;
    }
}
