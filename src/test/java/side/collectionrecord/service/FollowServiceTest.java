package side.collectionrecord.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.UserFollowingRequestDto;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FollowServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FollowService followService;

    @Test
    public void 팔로잉(){
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

        UserFollowingRequestDto userFollowingRequestDto = UserFollowingRequestDto.builder()
                .followingUserId(user1.getId())
                .userId(user2.getId())
                .build();

        followService.following(userFollowingRequestDto);

        assertThat(user1.getFollower().get(0).getFollowing()).isEqualTo(user2);
        assertThat(user2.getFollowing().get(0).getFollower()).isEqualTo(user1);
    }
}