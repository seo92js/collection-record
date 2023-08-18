package side.collectionrecord.domain.posts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.domain.user.UserRole;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save(){
        User user = User.builder()
                .username("user")
                .password("password")
                .profileImage(null)
                .userRole(UserRole.USER)
                .build();

        userRepository.save(user);

        Posts posts = Posts.builder()
                .artist("artist")
                .album("album")
                .genre("genre")
                .albumArt("albumArt")
                .category(Category.TAPE)
                .representativeImage(null)
                .text("text")
                .category(Category.CD)
                .build();

        postsRepository.save(posts);

        List<Posts> all = postsRepository.findAll();

        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void 게시물_리스트(){
        //given
        User user = User.builder()
                .username("user")
                .userRole(UserRole.USER)
                .password("password")
                .profileImage(null)
                .build();

        userRepository.save(user);

        Posts post1 = Posts.builder()
                .user(user)
                .artist("artist")
                .album("album")
                .genre("genre")
                .albumArt("albumArt")
                .category(Category.TAPE)
                .representativeImage(null)
                .text("text1")
                .status(PostsStatus.SALE)
                .build();

        postsRepository.save(post1);

        Posts post2 = Posts.builder()
                .user(user)
                .artist("artist")
                .album("album")
                .genre("genre")
                .albumArt("albumArt")
                .category(Category.TAPE)
                .representativeImage(null)
                .text("text2")
                .status(PostsStatus.SALE)
                .build();

        postsRepository.save(post2);

        //when
        List<Posts> postsList = postsRepository.findByUserIdAndCategory(user.getId(), "TAPE", 0, 9);

        //then
        assertThat(postsList.size()).isEqualTo(2);

    }

    @Test
    public void 카테고리로_찾기(){
        //given
        User user = User.builder()
                .username("user")
                .userRole(UserRole.USER)
                .password("password")
                .profileImage(null)
                .build();

        userRepository.save(user);

        Posts post1 = Posts.builder()
                .user(user)
                .artist("artist")
                .album("album")
                .genre("genre")
                .albumArt("albumArt")
                .category(Category.TAPE)
                .representativeImage(null)
                .text("text1")
                .build();

        postsRepository.save(post1);

        Posts post2 = Posts.builder()
                .user(user)
                .artist("artist")
                .album("album")
                .genre("genre")
                .albumArt("albumArt")
                .category(Category.TAPE)
                .representativeImage(null)
                .text("text2")
                .build();

        postsRepository.save(post2);

        //when
        List<Posts> hashtags = postsRepository.findByUserIdAndCategory(user.getId(), "TAPE", 0, 5);

        //then
        assertThat(hashtags.size()).isEqualTo(2);
    }
}