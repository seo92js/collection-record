package side.collectionrecord.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.comment.Comment;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserTest {

    @Test
    void 연관관계세팅(){
        //given
        User user = User.builder()
                .username("test")
                .password("test")
                .profileImage(null)
                .build();

        Category category = Category.builder()
                .user(user)
                .name("test")
                .build();

        Posts posts = Posts.builder()
                .user(user)
                .category(category)
                .title("test")
                .representativeImage(null)
                .text("text")
                .status(PostsStatus.SALE)
                .build();

        Comment comment = Comment.builder()
                .user(user)
                .posts(posts)
                .text("text")
                .build();

        //when
        //then
        assertThat(user.getCategories()).containsExactly(category);
        assertThat(user).isEqualTo(category.getUser());

        assertThat(user.getComments()).containsExactly(comment);
        assertThat(user).isEqualTo(comment.getUser());

        assertThat(category.getPosts()).containsExactly(posts);
        assertThat(category).isEqualTo(posts.getCategory());

        assertThat(posts.getComments()).containsExactly(comment);
        assertThat(posts).isEqualTo(comment.getPosts());
    }

    @Test
    void 빌더패턴(){
        //given
        User user = User.builder()
                .username("test")
                .password("test")
                .profileImage(null)
                .build();

        //when
        //then
        assertThat(user.getUsername()).isEqualTo("test");
        assertThat(user.getPassword()).isEqualTo("test");
        assertThat(user.getProfileImage()).isNull();
    }
}