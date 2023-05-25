package side.collectionrecord.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.BaseTimeEntity;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "posts_id")
    private Long id;

    private String title;
    private String text;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // 연관관계 편의
    public void setCategory(Category category){
        this.category = category;
        category.addPosts(this);
    }

    // User는 단방향이니 생성자에 넣지 말자.. 나중에 확인 필요
    @Builder
    public Posts(Category category, String title, String image, String text){
        setCategory(category);
        this.title = title;
        this.image = image;
        this.text = text;
    }
}
