package side.collectionrecord.domain.category;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.user.User;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CategoryTest {
    @Test
    public void 연관관계세팅(){
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

        //then
        assertThat(user).isEqualTo(category.getUser());
        assertThat(user.getCategories()).containsExactly(category);
    }

    @Test
    public void 빌더패턴(){
        //given
        User user = new User();

        String name = "name";

        Category category = Category.builder()
                .user(user)
                .name(name)
                .build();

        //then
        assertThat(category.getName()).isEqualTo(name);
    }

}