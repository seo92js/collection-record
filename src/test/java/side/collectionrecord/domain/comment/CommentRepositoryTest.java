package side.collectionrecord.domain.comment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.category.CategoryRepository;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void save(){
        //given
        User user = User.builder()
                .username("user")
                .password("1234")
                .image(null)
                .build();

        userRepository.save(user);

        Category category = Category.builder()
                .user(user)
                .name("category")
                .build();

        categoryRepository.save(category);

        Posts posts = Posts.builder()
                .text("text")
                .image(null)
                .title("title")
                .user(user)
                .category(category)
                .build();

        postsRepository.save(posts);

        Comment comment = Comment.builder()
                .text("comment")
                .posts(posts)
                .user(user)
                .build();

        commentRepository.save(comment);

        //when
        List<User> users = userRepository.findAll();
        List<Comment> comments = commentRepository.findAll();

        assertThat(comments.size()).isEqualTo(1);
        assertThat(users.get(0).getComments().size()).isEqualTo(1);
    }

    @Test
    public void 게시물의_모든댓글찾기(){
        //given
        User user = User.builder()
                .username("user")
                .password("1234")
                .image(null)
                .build();

        userRepository.save(user);

        Category category = Category.builder()
                .user(user)
                .name("category")
                .build();

        categoryRepository.save(category);

        Posts posts = Posts.builder()
                .text("text")
                .image(null)
                .title("title")
                .user(user)
                .category(category)
                .build();

        postsRepository.save(posts);

        Comment comment1 = Comment.builder()
                .text("comment1")
                .posts(posts)
                .user(user)
                .build();

        commentRepository.save(comment1);

        Comment comment2 = Comment.builder()
                .text("comment2")
                .posts(posts)
                .user(user)
                .build();

        commentRepository.save(comment2);

        //when
        List<Comment> allComments = commentRepository.findAllComments(posts);

        //then
        assertThat(allComments.size()).isEqualTo(2);
    }
}