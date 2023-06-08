package side.collectionrecord.domain.follow;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.user.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

        assertThat(user1.getFollowing()).containsExactly(follow);
        assertThat(user2.getFollower()).containsExactly(follow);
    }
}