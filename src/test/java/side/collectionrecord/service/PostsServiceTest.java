package side.collectionrecord.service;

import org.assertj.core.api.Assertions;
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

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

    @Autowired
    EntityManager em;

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
                .categoryId(category.getId())
                .title("title")
                .image("image")
                .text("text")
                .build();


        Long id = postsService.addPosts(postsAddRequestDto);


        Posts posts = postsRepository.findById(id).get();

        assertThat(posts.getUser()).isEqualTo(user);
        assertThat(posts.getCategory()).isEqualTo(category);
        assertThat(posts.getTitle()).isEqualTo("title");
        assertThat(posts.getImage()).isEqualTo("image");
        assertThat(posts.getText()).isEqualTo("text");
    }
}