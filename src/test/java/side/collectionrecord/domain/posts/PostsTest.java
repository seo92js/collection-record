package side.collectionrecord.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.user.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsTest {

    @Test
    public void 연간관계세팅(){
        //given
        User user = User.builder()
                .username("user1")
                .password("password1")
                .image("image1")
                .build();

        Category category = Category.builder()
                .user(user)
                .name("category1")
                .build();

        //when
        //생성자에 setCategory 함수 포함 되어있음.
        Posts posts = Posts.builder()
                .title("title")
                .image("image")
                .text("text")
                .category(category)
                .build();

        //then
        assertThat(user).isEqualTo(category.getUser());
        assertThat(user.getCategories()).containsExactly(category);
        assertThat(category).isEqualTo(posts.getCategory());
        assertThat(category.getPosts()).containsExactly(posts);
    }

    @Test
    public void 빌더패턴(){
        //given
        String title = "title";
        String image = "image";
        String text = "text";

        Category category = new Category();

        //when
        Posts posts = Posts.builder()
                .title(title)
                .image(image)
                .text(text)
                .category(category)
                .build();

        //then
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getText()).isEqualTo(text);
        assertThat(posts.getImage()).isEqualTo(image);
    }
}