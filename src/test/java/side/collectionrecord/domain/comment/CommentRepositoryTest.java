package side.collectionrecord.domain.comment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsRepository;
import side.collectionrecord.domain.posts.PostsStatus;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.domain.user.UserRole;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @AfterEach
    public void cleanup(){
        commentRepository.deleteAll();
        postsRepository.deleteAll();
        categoryRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void save(){
        //given
        User user = User.builder()
                .username("user")
                .userRole(UserRole.USER)
                .password("1234")
                .profileImage(null)
                .build();

        userRepository.save(user);

        Category category = Category.builder()
                .user(user)
                .name("category")
                .build();

        categoryRepository.save(category);

        Posts posts = Posts.builder()
                .text("text")
                .representativeImage(null)
                .title("title")
                .user(user)
                .category(category)
                .status(PostsStatus.SALE)
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
                .userRole(UserRole.USER)
                .password("1234")
                .profileImage(null)
                .build();

        userRepository.save(user);

        Category category = Category.builder()
                .user(user)
                .name("category")
                .build();

        categoryRepository.save(category);

        Posts posts = Posts.builder()
                .text("text")
                .representativeImage(null)
                .title("title")
                .user(user)
                .category(category)
                .status(PostsStatus.SALE)
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
        List<Comment> allComments = commentRepository.findParentCommentByPosts(posts);

        //then
        assertThat(allComments.size()).isEqualTo(2);
    }
}