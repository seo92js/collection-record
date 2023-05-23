package side.collectionrecord.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.CategoryAddRequestDto;
import side.collectionrecord.web.dto.CategoryListResponseDto;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 카테고리추가(){
        //given
        User user = User.builder()
                .username("test")
                .password("test")
                .image(null)
                .build();

        userRepository.save(user);

        CategoryAddRequestDto categoryAddRequestDto = CategoryAddRequestDto.builder()
                .userId(user.getId())
                .name("test")
                .build();

        Long categoryId = categoryService.addCategory(categoryAddRequestDto);

        em.flush();
        em.clear();

        //when
        List<CategoryListResponseDto> categories = categoryService.findCategories(user);

        //then
        Assertions.assertThat(categories.get(0).getName()).isEqualTo("test");
    }
}