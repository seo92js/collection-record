package side.collectionrecord.domain.follow;

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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class FollowRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    FollowRepository followRepository;

    @Test
    public void save(){
        //then
        User user1 = User.builder()
                .username("user1")
                .password("1")
                .profileImage(null)
                .build();

        userRepository.save(user1);

        User user2 = User.builder()
                .username("user2")
                .password("1")
                .profileImage(null)
                .build();

        userRepository.save(user2);

        Follow follow = Follow.builder()
                .following(user1)
                .follower(user2)
                .build();

        Follow saved = followRepository.save(follow);

        //when
        List<Follow> all = followRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getFollowing().getUsername()).isEqualTo("user1");
        assertThat(all.get(0).getFollower().getUsername()).isEqualTo("user2");
        assertThat(all.get(0)).isEqualTo(saved);
    }

    @Test
    public void 팔로잉한_유저_게시물찾기(){
        //then
        User user1 = User.builder()
                .username("user1")
                .password("1")
                .profileImage(null)
                .build();

        userRepository.save(user1);

        User user2 = User.builder()
                .username("user2")
                .password("1")
                .profileImage(null)
                .build();

        userRepository.save(user2);

        Category category = Category.builder()
                .user(user2)
                .name("category")
                .build();

        categoryRepository.save(category);

        Posts posts1 = Posts.builder()
                .user(user2)
                .category(category)
                .title("title1")
                .representativeImage(null)
                .text("text1")
                .build();

        postsRepository.save(posts1);

        Posts posts2 = Posts.builder()
                .user(user2)
                .category(category)
                .title("title2")
                .representativeImage(null)
                .text("text2")
                .build();

        postsRepository.save(posts2);

        Follow follow = Follow.builder()
                .following(user1)
                .follower(user2)
                .build();

        Follow saved = followRepository.save(follow);

        List<Posts> followPosts = followRepository.findFollowPosts(user1.getId());
        assertThat(followPosts.size()).isEqualTo(2);
        assertThat(followPosts.get(0).getTitle()).isEqualTo("title1");
        assertThat(followPosts.get(1).getTitle()).isEqualTo("title2");
    }
}