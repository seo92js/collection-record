package side.collectionrecord.domain.comment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.user.User;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CommentTest {

    @Test
    public void 연관관계세팅(){
        //given
        User user = User.builder()
                .username("test")
                .password("test")
                .profileImage(null)
                .build();

        Category category = new Category();

        Posts posts = Posts.builder()
                .user(user)
                .category(category)
                .title("test")
                .representativeImage(null)
                .text("text")
                .build();

        Comment comment = Comment.builder()
                .user(user)
                .posts(posts)
                .text("text")
                .build();

        //when
        //then
        assertThat(user.getComments()).containsExactly(comment);
        assertThat(user).isEqualTo(comment.getUser());

        assertThat(posts.getComments()).containsExactly(comment);
        assertThat(posts).isEqualTo(comment.getPosts());
    }

    @Test
    public void 빌더패턴(){
        Comment comment = Comment.builder()
                .user(new User())
                .posts(new Posts())
                .text("text")
                .build();

        assertThat(comment.getText()).isEqualTo("text");
    }
}