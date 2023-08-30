package side.collectionrecord.domain.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.BaseTimeEntity;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id", referencedColumnName = "comment_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> childComments = new ArrayList<>();

    public void setUser(User user){
        this.user = user;
        user.addComment(this);
    }

    public void setPosts(Posts posts){
        this.posts = posts;
        posts.addComment(this);
    }

    public void setParentComment(Comment parentComment){
        this.parentComment = parentComment;
        parentComment.getChildComments().add(this);
    }

    @Builder
    public Comment(User user, Posts posts, String text, Comment parentComment){
        setUser(user);
        setPosts(posts);
        if(parentComment != null)
            setParentComment(parentComment);
        this.text = text;
    }
}
