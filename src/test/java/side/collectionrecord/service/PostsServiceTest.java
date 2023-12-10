package side.collectionrecord.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsRepository;
import side.collectionrecord.domain.posts.PostsStatus;
import side.collectionrecord.domain.user.Role;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.exception.PostsNotFoundException;
import side.collectionrecord.web.dto.PostsAddForm;
import side.collectionrecord.web.dto.PostsUpdateForm;

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
                .role(Role.USER)
                .profileImage(null)
                .profileText(null)
                .email("email")
                .build();

        userRepository.save(user);

        byte[] data = {0,0};

        Image image = Image.builder()
                .filename("image")
                .data(data)
                .build();

        imageRepository.save(image);

        List<Image> images = new ArrayList<>();
        images.add(image);

        PostsAddForm postsAddForm = PostsAddForm.builder()
                .userId(user.getId())
                .artist("artist")
                .album("album")
                .genre("genre")
                .albumArt("albumArt")
                .category(Category.CD)
                .images(null)
                .text("text")
                .status(PostsStatus.SALE)
                .build();


        Long id = postsService.postsAdd(postsAddForm, images);

        //when
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new PostsNotFoundException("게시물이 없습니다."));

        //then
        assertThat(posts.getUser()).isEqualTo(user);
        assertThat(posts.getCategory()).isEqualTo(Category.CD);
        assertThat(posts.getImages().get(0).getFilename()).isEqualTo("image");
        assertThat(posts.getText()).isEqualTo("text");
        assertThat(posts.getStatus()).isEqualTo(PostsStatus.SALE);
    }

    @Test
    public void 게시물_삭제(){
        //given
        User user = User.builder()
                .username("user1")
                .role(Role.USER)
                .profileImage(null)
                .profileText(null)
                .email("email")
                .build();

        userRepository.save(user);

        byte[] data = {0,0};

        Image image = Image.builder()
                .filename("image1")
                .data(data)
                .build();

        imageRepository.save(image);

        List<Image> images = new ArrayList<>();
        images.add(image);

        PostsAddForm postsAddForm1 = PostsAddForm.builder()
                .userId(user.getId())
                .artist("artist")
                .album("album")
                .genre("genre")
                .albumArt("albumArt")
                .category(Category.CD)
                .images(null)
                .status(PostsStatus.SALE)
                .text("text2")
                .build();

        PostsAddForm postsAddForm2 = PostsAddForm.builder()
                .userId(user.getId())
                .artist("artist")
                .album("album")
                .genre("genre")
                .albumArt("albumArt")
                .category(Category.CD)
                .images(null)
                .status(PostsStatus.SALE)
                .text("text2")
                .build();

        postsService.postsAdd(postsAddForm1, images);

        Long id = postsService.postsAdd(postsAddForm2, images);

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
                .role(Role.USER)
                .profileImage(null)
                .profileText(null)
                .email("email")
                .build();

        userRepository.save(user);

        byte[] data = {0,};

        Image image = Image.builder()
                .filename("")
                .data(data)
                .build();

        imageRepository.save(image);

        List<Image> images = new ArrayList<>();
        images.add(image);

        PostsAddForm postsAddForm = PostsAddForm.builder()
                .userId(user.getId())
                .artist("artist")
                .album("album")
                .genre("genre")
                .albumArt("albumArt")
                .category(Category.CD)
                .images(null)
                .status(PostsStatus.SALE)
                .text("text")
                .build();

        Long id = postsService.postsAdd(postsAddForm, images);

        String expectedText = "text2";
        PostsStatus expectedStatus = PostsStatus.SOLD_OUT;

        //when
        PostsUpdateForm postsUpdateForm = PostsUpdateForm.builder()
                .status(expectedStatus)
                .text(expectedText)
                .build();

        postsService.updatePosts(id, postsUpdateForm);

        //then
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new PostsNotFoundException("게시물이 없습니다."));

        assertThat(posts.getCategory()).isEqualTo(Category.CD);
        assertThat(posts.getText()).isEqualTo(expectedText);
        assertThat(posts.getStatus()).isEqualTo(expectedStatus);
    }
}