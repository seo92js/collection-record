package side.collectionrecord.domain.category;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CategoryRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    public void save(){
        //given
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

        //when
        List<Category> allCategory = categoryRepository.findAllCategory(user.getId());

        //then
        assertThat(allCategory.get(0).getName()).isEqualTo("test");
    }

    @Test
    public void 이름으로_찾기(){
        //given
        User user = User.builder()
                .username("userA")
                .password("1")
                .image(null)
                .build();

        userRepository.save(user);

        Category category1 = Category.builder()
                .user(user)
                .name("category1")
                .build();

        Category category2 = Category.builder()
                .user(user)
                .name("category2")
                .build();

        categoryRepository.save(category1);
        categoryRepository.save(category2);

        //when
        Category findCategory1 = categoryRepository.findByName(user.getId(), "category1");
        Category findCategory2 = categoryRepository.findByName(user.getId(), "category2");

        //then
        assertThat(findCategory1).isNotNull();
        assertThat(findCategory2).isNotNull();
    }

    @Test
    public void 모든카테고리_찾기(){
        //given
        User user = User.builder()
                .username("userA")
                .password("1")
                .image(null)
                .build();

        userRepository.save(user);

        Category category1 = Category.builder()
                .user(user)
                .name("category1")
                .build();

        Category category2 = Category.builder()
                .user(user)
                .name("category2")
                .build();

        categoryRepository.save(category1);
        categoryRepository.save(category2);

        //when
        List<Category> allCategory = categoryRepository.findAllCategory(user.getId());

        //then
        assertThat(allCategory.size()).isEqualTo(2);
    }
}