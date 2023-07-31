package side.collectionrecord.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.BaseTimeEntity;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.comment.Comment;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "posts_id")
    private Long id;

    private String title;
    private String text;

    @OneToOne
    @JoinColumn(name = "image_id")
    private List<Image> representativeImage;

    private String hashtags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    private PostsStatus status;

    @OneToMany(mappedBy = "posts")
    private List<Comment> comments = new ArrayList<>();

    // 연관관계 편의
    public void setCategory(Category category){
        this.category = category;
        category.addPosts(this);
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    @Builder
    public Posts(User user, Category category, String title, List<Image> representativeImage, String text, String hashtags, PostsStatus status){
        setCategory(category);
        this.user = user;
        this.title = title;
        this.representativeImage = representativeImage;
        this.text = text;
        this.hashtags = hashtags;
        this.status = status;
    }

    public void update(Category category, String title, List<Image> representativeImage, String text, String hashtags, PostsStatus status){
        this.category = category;
        this.title = title;
        this.representativeImage = representativeImage;
        this.text = text;
        this.hashtags = hashtags;
        this.status = status;
    }
}
