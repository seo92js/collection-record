package side.collectionrecord.service;

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
import side.collectionrecord.web.dto.PostsAddRequestDto;
import side.collectionrecord.web.dto.PostsUpdateRequestDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostsServiceTest {
    @Autowired
    PostsService postsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PostsRepository postsRepository;

    @Test
    public void 게시물_추가(){
        //given
        User user = User.builder()
                .username("user1")
                .password("password1")
                .image("image1")
                .build();

        userRepository.save(user);

        Category category = Category.builder()
                .user(user)
                .name("category1")
                .build();

        categoryRepository.save(category);

        PostsAddRequestDto postsAddRequestDto = PostsAddRequestDto.builder()
                .userId(user.getId())
                .categoryName(category.getName())
                .title("title")
                .image("image")
                .text("text")
                .build();


        Long id = postsService.addPosts(user.getId(), postsAddRequestDto);

        //when
        Posts posts = postsRepository.findById(id).get();

        //then
        assertThat(posts.getUser()).isEqualTo(user);
        assertThat(posts.getCategory()).isEqualTo(category);
        assertThat(posts.getTitle()).isEqualTo("title");
        assertThat(posts.getImage()).isEqualTo("image");
        assertThat(posts.getText()).isEqualTo("text");
    }

    @Test
    public void 게시물_삭제(){
        //given
        User user = User.builder()
                .username("user1")
                .password("password1")
                .image("image1")
                .build();

        userRepository.save(user);

        Category category = Category.builder()
                .user(user)
                .name("category1")
                .build();

        categoryRepository.save(category);

        PostsAddRequestDto postsAddRequestDto1 = PostsAddRequestDto.builder()
                .userId(user.getId())
                .categoryName(category.getName())
                .title("title2")
                .image("image2")
                .text("text2")
                .build();

        PostsAddRequestDto postsAddRequestDto2 = PostsAddRequestDto.builder()
                .userId(user.getId())
                .categoryName(category.getName())
                .title("title2")
                .image("image2")
                .text("text2")
                .build();

        postsService.addPosts(user.getId(), postsAddRequestDto1);

        Long id = postsService.addPosts(user.getId(), postsAddRequestDto2);

        List<Posts> all = postsRepository.findAll();

        assertThat(all.size()).isEqualTo(2);

        //when
        postsService.delete(id);

        //then
        all = postsRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void 게시물_업데이트(){
        //given
        User user = User.builder()
                .username("user1")
                .password("password1")
                .image("image1")
                .build();

        userRepository.save(user);

        Category category = Category.builder()
                .user(user)
                .name("category1")
                .build();

        categoryRepository.save(category);

        PostsAddRequestDto postsAddRequestDto1 = PostsAddRequestDto.builder()
                .userId(user.getId())
                .categoryName(category.getName())
                .title("title")
                .image("image")
                .text("text")
                .build();

        Long id = postsService.addPosts(user.getId(), postsAddRequestDto1);

        String expectedCategoryName = "category2";
        String expectedTitle = "title2";
        String expectedImage = "image2";
        String expectedText = "text2";

        Category expectedCategory = Category.builder()
                .user(user)
                .name(expectedCategoryName)
                .build();

        categoryRepository.save(expectedCategory);

        //when
        PostsUpdateRequestDto postsUpdateRequestDto = PostsUpdateRequestDto.builder()
                .categoryName(expectedCategory.getName())
                .title(expectedTitle)
                .image(expectedImage)
                .text(expectedText)
                .build();

        postsService.update(user.getId(), id, postsUpdateRequestDto);

        //then
        Posts posts = postsRepository.findById(id).orElse(null);

        assertThat(posts.getCategory()).isEqualTo(expectedCategory);
        assertThat(posts.getCategory()).isNotEqualTo(category);
        assertThat(posts.getTitle()).isEqualTo(expectedTitle);
        assertThat(posts.getImage()).isEqualTo(expectedImage);
        assertThat(posts.getText()).isEqualTo(expectedText);
    }
}