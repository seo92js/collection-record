package side.collectionrecord.domain.posts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.category.CategoryRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;

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
                .build();

        postsRepository.save(posts);

        List<Posts> all = postsRepository.findAll();

        assertThat(all.size()).isEqualTo(1);
    }
}