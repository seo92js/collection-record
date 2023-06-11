package side.collectionrecord.domain.follow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.user.User;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FollowTest {
    @Test
    public void 연관관계_세팅(){
        //given
        User user1 = User.builder()
                .username("user1")
                .password("1")
                .image("1")
                .build();

        User user2 = User.builder()
                .username("user2")
                .password("1")
                .image("1")
                .build();

        Follow follow = Follow.builder()
                .following(user1)
                .follower(user2)
                .build();

        //then
        assertThat(user1.getFollowing()).containsExactly(follow);
        assertThat(user2.getFollower()).containsExactly(follow);
    }

    @Test
    public void 빌더패턴(){
        //given
        User user1 = User.builder()
                .username("user1")
                .password("1")
                .image("1")
                .build();

        User user2 = User.builder()
                .username("user2")
                .password("1")
                .image("1")
                .build();

        //when
        Follow follow = Follow.builder()
                .following(user1)
                .follower(user2)
                .build();

        //then
        assertThat(follow.getFollowing().getUsername()).isEqualTo("user1");
        assertThat(follow.getFollower().getUsername()).isEqualTo("user2");
    }
}