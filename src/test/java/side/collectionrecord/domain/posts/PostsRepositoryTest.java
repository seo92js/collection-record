package side.collectionrecord.domain.posts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.category.CategoryRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void save(){
        User user = User.builder()
                .username("user")
                .password("password")
                .profileImage(null)
                .build();

        userRepository.save(user);

        Category category = Category.builder()
                .user(user)
                .name("category")
                .build();

        categoryRepository.save(category);

        Posts posts = Posts.builder()
                .title("title")
                .representativeImage(null)
                .text("text")
                .category(category)
                .hashtags("hashtags")
                .build();

        postsRepository.save(posts);

        List<Posts> all = postsRepository.findAll();

        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void 게시물_리스트(){
        //given
        User user = User.builder()
                .username("user")
                .password("password")
                .profileImage(null)
                .build();

        userRepository.save(user);

        Category category = Category.builder()
                .user(user)
                .name("category")
                .build();

        categoryRepository.save(category);

        Posts post1 = Posts.builder()
                .title("title1")
                .representativeImage(null)
                .text("text1")
                .category(category)
                .hashtags("hashtags")
                .build();

        postsRepository.save(post1);

        Posts post2 = Posts.builder()
                .title("title2")
                .representativeImage(null)
                .text("text2")
                .category(category)
                .hashtags("hashtags")
                .build();

        postsRepository.save(post2);

        //when
        List<Posts> postsList = postsRepository.findPostsList(user.getId(), category.getName());

        //then
        assertThat(postsList.size()).isEqualTo(2);

    }

    @Test
    public void 해시태그로_찾기(){
        //given
        User user = User.builder()
                .username("user")
                .password("password")
                .profileImage(null)
                .build();

        userRepository.save(user);

        Category category = Category.builder()
                .user(user)
                .name("category")
                .build();

        categoryRepository.save(category);

        Posts post1 = Posts.builder()
                .title("title1")
                .representativeImage(null)
                .text("text1")
                .category(category)
                .hashtags("hashtags")
                .build();

        postsRepository.save(post1);

        Posts post2 = Posts.builder()
                .title("title2")
                .representativeImage(null)
                .text("text2")
                .category(category)
                .hashtags("hashtags")
                .build();

        postsRepository.save(post2);

        //when
        List<Posts> hashtags = postsRepository.findContainsHashtag("hashtags");

        //then
        assertThat(hashtags.size()).isEqualTo(2);
    }
}