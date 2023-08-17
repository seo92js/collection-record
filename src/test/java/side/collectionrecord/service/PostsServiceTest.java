package side.collectionrecord.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsRepository;
import side.collectionrecord.domain.posts.PostsStatus;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.domain.user.UserRole;
import side.collectionrecord.web.dto.CreatePostsRequestDto;
import side.collectionrecord.web.dto.UpdatePostsRequestDto;

import java.util.ArrayList;
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
    PostsRepository postsRepository;

    @Autowired
    ImageRepository imageRepository;

    @Test
    public void 게시물_추가(){
        //given
        User user = User.builder()
                .username("user1")
                .userRole(UserRole.USER)
                .password("password1")
                .profileImage(null)
                .build();

        userRepository.save(user);

        Category category = Category.builder()
                .user(user)
                .name("category1")
                .build();

        categoryRepository.save(category);

        byte[] data = {0,0};

        Image image = Image.builder()
                .filename("image")
                .data(data)
                .build();

        imageRepository.save(image);

        List<Image> images = new ArrayList<>();
        images.add(image);

        CreatePostsRequestDto createPostsRequestDto = CreatePostsRequestDto.builder()
                .userId(user.getId())
                .categoryId(category.getId())
                .title("title")
                .representativeImage(images)
                .text("text")
                .status(PostsStatus.SALE)
                .build();


        Long id = postsService.createPosts(createPostsRequestDto);

        //when
        Posts posts = postsRepository.findById(id).get();

        //then
        assertThat(posts.getUser()).isEqualTo(user);
        assertThat(posts.getCategory()).isEqualTo(category);
        assertThat(posts.getTitle()).isEqualTo("title");
        assertThat(posts.getRepresentativeImage().get(0).getFilename()).isEqualTo("image");
        assertThat(posts.getText()).isEqualTo("text");
        assertThat(posts.getStatus()).isEqualTo(PostsStatus.SALE);
    }

    @Test
    public void 게시물_삭제(){
        //given
        User user = User.builder()
                .username("user1")
                .userRole(UserRole.USER)
                .password("password1")
                .profileImage(null)
                .build();

        userRepository.save(user);

        Category category = Category.builder()
                .user(user)
                .name("category1")
                .build();

        categoryRepository.save(category);

        byte[] data = {0,0};

        Image image = Image.builder()
                .filename("image1")
                .data(data)
                .build();

        imageRepository.save(image);

        List<Image> images = new ArrayList<>();
        images.add(image);

        CreatePostsRequestDto createPostsRequestDto1 = CreatePostsRequestDto.builder()
                .userId(user.getId())
                .categoryId(category.getId())
                .title("title2")
                .representativeImage(images)
                .status(PostsStatus.SALE)
                .text("text2")
                .build();

        CreatePostsRequestDto createPostsRequestDto2 = CreatePostsRequestDto.builder()
                .userId(user.getId())
                .categoryId(category.getId())
                .title("title2")
                .representativeImage(images)
                .status(PostsStatus.SALE)
                .text("text2")
                .build();

        postsService.createPosts(createPostsRequestDto1);

        Long id = postsService.createPosts(createPostsRequestDto2);

        List<Posts> all = postsRepository.findAll();

        assertThat(all.size()).isEqualTo(2);

        //when
        postsService.deletePosts(id);

        //then
        all = postsRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void 게시물_업데이트(){
        //given
        User user = User.builder()
                .username("user1")
                .userRole(UserRole.USER)
                .password("password1")
                .profileImage(null)
                .build();

        userRepository.save(user);

        Category category = Category.builder()
                .user(user)
                .name("category1")
                .build();

        categoryRepository.save(category);

        byte[] data = {0,};

        Image image = Image.builder()
                .filename("")
                .data(data)
                .build();

        imageRepository.save(image);

        List<Image> images = new ArrayList<>();
        images.add(image);

        CreatePostsRequestDto createPostsRequestDto1 = CreatePostsRequestDto.builder()
                .userId(user.getId())
                .categoryId(category.getId())
                .title("title")
                .representativeImage(images)
                .status(PostsStatus.SALE)
                .text("text")
                .build();

        Long id = postsService.createPosts(createPostsRequestDto1);

        String expectedCategoryName = "category2";
        String expectedTitle = "title2";
        String expectedText = "text2";
        PostsStatus expectedStatus = PostsStatus.SOLD_OUT;

        Category expectedCategory = Category.builder()
                .user(user)
                .name(expectedCategoryName)
                .build();

        categoryRepository.save(expectedCategory);

        //when
        UpdatePostsRequestDto updatePostsRequestDto = UpdatePostsRequestDto.builder()
                .categoryId(expectedCategory.getId())
                .title(expectedTitle)
                .status(expectedStatus)
                .text(expectedText)
                .build();

        postsService.updatePosts(user.getId(), id, updatePostsRequestDto);

        //then
        Posts posts = postsRepository.findById(id).orElse(null);

        assertThat(posts.getCategory()).isEqualTo(expectedCategory);
        assertThat(posts.getCategory()).isNotEqualTo(category);
        assertThat(posts.getTitle()).isEqualTo(expectedTitle);
        assertThat(posts.getText()).isEqualTo(expectedText);
        assertThat(posts.getStatus()).isEqualTo(expectedStatus);
    }
}