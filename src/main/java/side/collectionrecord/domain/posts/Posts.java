package side.collectionrecord.domain.posts;

import side.collectionrecord.domain.BaseTimeEntity;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.user.User;

import javax.persistence.*;

@Entity
public class Posts extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "posts_id")
    private Long id;

    private String title;
    private String text;

    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
