package side.collectionrecord.domain.category;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;

import java.util.List;

@SpringBootTest
@Transactional
class CategoryRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    public void save(){
        User user = User.builder()
                .username("userA")
                .password("1")
                .image(null)
                .build();

        userRepository.save(user);

        Category category = Category.builder()
                .user(user)
                .name("test")
                .build();

        categoryRepository.save(category);

        List<Category> allCategory = categoryRepository.findAllCategory(user);

        Assertions.assertThat(allCategory.get(0).getName()).isEqualTo("test");
    }
}