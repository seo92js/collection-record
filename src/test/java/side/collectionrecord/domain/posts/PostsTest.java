package side.collectionrecord.domain.posts;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.user.User;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostsTest {

    @Test
    public void 연관관계세팅(){
        //given
        User user = User.builder()
                .username("user1")
                .password("password1")
                .profileImage(null)
                .build();

        //when
        //생성자에 setCategory 함수 포함 되어있음.
        Posts posts = Posts.builder()
                .user(user)
                .artist("artist")
                .album("album")
                .genre("genre")
                .albumArt("albumArt")
                .category(Category.CD)
                .representativeImage(null)
                .text("text")
                .status(PostsStatus.SALE)
                .build();

        //then
        assertThat(posts.getCategory()).isEqualTo("CD");
        assertThat(posts.getStatus()).isEqualTo(PostsStatus.SALE);
    }

    @Test
    public void 빌더패턴(){
        //given
        String title = "title";
        String text = "text";

        //when
        Posts posts = Posts.builder()
                .artist("artist")
                .album("album")
                .genre("genre")
                .albumArt("albumArt")
                .category(Category.CD)
                .representativeImage(null)
                .text(text)
                .status(PostsStatus.SALE)
                .build();

        //then
        assertThat(posts.getText()).isEqualTo(text);
        assertThat(posts.getRepresentativeImage()).isNull();
        assertThat(posts.getStatus()).isEqualTo(PostsStatus.SALE);
    }
}