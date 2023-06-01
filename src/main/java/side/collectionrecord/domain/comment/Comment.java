package side.collectionrecord.domain.comment;

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
}
