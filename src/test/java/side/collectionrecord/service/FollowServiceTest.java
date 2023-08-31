package side.collectionrecord.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.user.Role;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.CreateFollowRequestDto;

import static org.assertj.core.api.Assertions.assertThat;

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
                .role(Role.USER)
                .profileImage(null)
                .profileText(null)
                .email("email")
                .build();

        userRepository.save(user1);

        User user2 = User.builder()
                .username("user2")
                .role(Role.USER)
                .profileImage(null)
                .profileText(null)
                .email("email")
                .build();

        userRepository.save(user2);

        CreateFollowRequestDto createFollowRequestDto = CreateFollowRequestDto.builder()
                .followingUserId(user1.getId())
                .userId(user2.getId())
                .build();

        followService.createFollow(createFollowRequestDto);

        assertThat(user1.getFollower().get(0).getFollowing()).isEqualTo(user2);
        assertThat(user2.getFollowing().get(0).getFollower()).isEqualTo(user1);
    }
}