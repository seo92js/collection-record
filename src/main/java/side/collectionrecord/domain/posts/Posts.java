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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posts_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String artist;
    private String album;
    private String albumArt;
    private String genre;
    private String text;

    @OneToMany
    @JoinColumn(name = "posts_id")
    private List<Image> images = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private PostsStatus status;

    @OneToMany(mappedBy = "posts")
    private List<Comment> comments = new ArrayList<>();

    public void setUser(User user){
        this.user = user;
        user.addPosts(this);
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    @Builder
    public Posts(User user, String artist, String album, String genre, String albumArt, Category category, List<Image> images, String text, PostsStatus status){
        this.user = user;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.albumArt = albumArt;
        this.category = category;
        this.images = images;
        this.text = text;
        this.status = status;
    }

    public void update(String text, PostsStatus status){
        this.text = text;
        this.status = status;
    }
}
