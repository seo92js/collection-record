package side.collectionrecord.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.domain.user.UserRole;
import side.collectionrecord.web.dto.CreateParentCategoryRequestDto;
import side.collectionrecord.web.dto.GetCategoryResponseDto;
import side.collectionrecord.web.dto.UpdateCategoryRequestDto;

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
                .userRole(UserRole.USER)
                .profileImage(null)
                .build();

        userRepository.save(user);

        CreateParentCategoryRequestDto createParentCategoryRequestDto = CreateParentCategoryRequestDto.builder()
                .userId(user.getId())
                .name("test")
                .build();

        Long categoryId = categoryService.createParentCategory(createParentCategoryRequestDto);

        em.flush();
        em.clear();

        //when
        List<GetCategoryResponseDto> categories = categoryService.getAllParentCategoryByUserId(user.getId());

        //then
        assertThat(categories.get(0).getName()).isEqualTo("test");
    }

    @Test
    public void 카테고리_리스트(){
        //given
        User user = User.builder()
                .username("user1")
                .password("password1")
                .userRole(UserRole.USER)
                .profileImage(null)
                .build();

        userRepository.save(user);

        CreateParentCategoryRequestDto createParentCategoryRequestDto1 = CreateParentCategoryRequestDto.builder()
                .userId(user.getId())
                .name("category1")
                .build();

        CreateParentCategoryRequestDto createParentCategoryRequestDto2= CreateParentCategoryRequestDto.builder()
                .userId(user.getId())
                .name("category2")
                .build();

        CreateParentCategoryRequestDto createParentCategoryRequestDto3 = CreateParentCategoryRequestDto.builder()
                .userId(user.getId())
                .name("category3")
                .build();

        categoryService.createParentCategory(createParentCategoryRequestDto1);
        categoryService.createParentCategory(createParentCategoryRequestDto2);
        categoryService.createParentCategory(createParentCategoryRequestDto3);

        //when
        List<GetCategoryResponseDto> categories = categoryService.getAllParentCategoryByUserId(user.getId());

        //then
        assertThat(categories.get(0).getName()).isEqualTo(createParentCategoryRequestDto1.getName());
        assertThat(categories.get(1).getName()).isEqualTo(createParentCategoryRequestDto2.getName());
        assertThat(categories.get(2).getName()).isEqualTo(createParentCategoryRequestDto3.getName());
    }

    @Test
    public void 카테고리_업데이트(){
        //given
        User user = User.builder()
                .username("user1")
                .password("password1")
                .userRole(UserRole.USER)
                .profileImage(null)
                .build();

        userRepository.save(user);

        CreateParentCategoryRequestDto createParentCategoryRequestDto = CreateParentCategoryRequestDto.builder()
                .userId(user.getId())
                .name("category1")
                .build();

        Long id = categoryService.createParentCategory(createParentCategoryRequestDto);

        String expectedName = "category2";

        UpdateCategoryRequestDto updateCategoryRequestDto = UpdateCategoryRequestDto.builder()
                .name(expectedName)
                .build();

        //when
        categoryService.updateCategory(id, updateCategoryRequestDto);

        //then
        List<GetCategoryResponseDto> categories = categoryService.getAllParentCategoryByUserId(user.getId());

        assertThat(categories.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    public void 카테고리_삭제(){
        //given
        User user = User.builder()
                .username("user1")
                .userRole(UserRole.USER)
                .password("password1")
                .profileImage(null)
                .build();

        userRepository.save(user);

        CreateParentCategoryRequestDto createParentCategoryRequestDto1 = CreateParentCategoryRequestDto.builder()
                .userId(user.getId())
                .name("category1")
                .build();

        CreateParentCategoryRequestDto createParentCategoryRequestDto2 = CreateParentCategoryRequestDto.builder()
                .userId(user.getId())
                .name("category2")
                .build();

        categoryService.createParentCategory(createParentCategoryRequestDto1);
        categoryService.createParentCategory(createParentCategoryRequestDto2);

        List<GetCategoryResponseDto> categories = categoryService.getAllParentCategoryByUserId(user.getId());

        //when
        categoryService.deleteCategory(categories.get(0).getId());

        //then
        categories = categoryService.getAllParentCategoryByUserId(user.getId());

        assertThat(categories.size()).isEqualTo(1);
    }
}