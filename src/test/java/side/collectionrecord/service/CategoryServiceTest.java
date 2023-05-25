package side.collectionrecord.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.CategoryAddRequestDto;
import side.collectionrecord.web.dto.CategoryListResponseDto;
import side.collectionrecord.web.dto.CategoryUpdateRequestDto;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
    public void 카테고리_추가(){
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
        assertThat(categories.get(0).getName()).isEqualTo("test");
    }

    @Test
    public void 카테고리_리스트(){
        //given
        User user = User.builder()
                .username("user1")
                .password("password1")
                .image(null)
                .build();

        userRepository.save(user);

        CategoryAddRequestDto categoryAddRequestDto1 = CategoryAddRequestDto.builder()
                .userId(user.getId())
                .name("category1")
                .build();

        CategoryAddRequestDto categoryAddRequestDto2 = CategoryAddRequestDto.builder()
                .userId(user.getId())
                .name("category2")
                .build();

        CategoryAddRequestDto categoryAddRequestDto3 = CategoryAddRequestDto.builder()
                .userId(user.getId())
                .name("category3")
                .build();

        categoryService.addCategory(categoryAddRequestDto1);
        categoryService.addCategory(categoryAddRequestDto2);
        categoryService.addCategory(categoryAddRequestDto3);

        //when
        List<CategoryListResponseDto> categories = categoryService.findCategories(user);

        //then
        assertThat(categories.get(0).getName()).isEqualTo(categoryAddRequestDto1.getName());
        assertThat(categories.get(1).getName()).isEqualTo(categoryAddRequestDto2.getName());
        assertThat(categories.get(2).getName()).isEqualTo(categoryAddRequestDto3.getName());
    }

    @Test
    public void 카테고리_업데이트(){
        //given
        User user = User.builder()
                .username("user1")
                .password("password1")
                .image(null)
                .build();

        userRepository.save(user);

        CategoryAddRequestDto categoryAddRequestDto = CategoryAddRequestDto.builder()
                .userId(user.getId())
                .name("category1")
                .build();

        Long id = categoryService.addCategory(categoryAddRequestDto);

        String expectedName = "category2";

        CategoryUpdateRequestDto categoryUpdateRequestDto = CategoryUpdateRequestDto.builder()
                .name(expectedName)
                .build();

        //when
        categoryService.update(id, categoryUpdateRequestDto);

        //then
        List<CategoryListResponseDto> categories = categoryService.findCategories(user);

        assertThat(categories.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    public void 카테고리_삭제(){
        //given
        User user = User.builder()
                .username("user1")
                .password("password1")
                .image(null)
                .build();

        userRepository.save(user);

        CategoryAddRequestDto categoryAddRequestDto1 = CategoryAddRequestDto.builder()
                .userId(user.getId())
                .name("category1")
                .build();

        CategoryAddRequestDto categoryAddRequestDto2 = CategoryAddRequestDto.builder()
                .userId(user.getId())
                .name("category2")
                .build();

        categoryService.addCategory(categoryAddRequestDto1);
        categoryService.addCategory(categoryAddRequestDto2);

        List<CategoryListResponseDto> categories = categoryService.findCategories(user);

        //when
        categoryService.delete(categories.get(0).getId());

        //then
        categories = categoryService.findCategories(user);

        assertThat(categories.size()).isEqualTo(1);
    }
}