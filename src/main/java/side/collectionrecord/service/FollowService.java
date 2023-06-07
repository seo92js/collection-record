package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.follow.Follow;
import side.collectionrecord.domain.follow.FollowRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.UserFollowingRequestDto;

@RequiredArgsConstructor
@Service
public class FollowService {
    private final UserRepository userRepository;

    private final FollowRepository followRepository;

    @Transactional
    public void following(UserFollowingRequestDto userFollowingRequestDto){
        User user = userRepository.findById(userFollowingRequestDto.getUserId()).get();
        User followingUser = userRepository.findById(userFollowingRequestDto.getFollowingUserId()).get();

        Follow follow = Follow.builder()
                .following(user)
                .follower(followingUser)
                .build();

        followRepository.save(follow);

        follow.addFollowing(user, followingUser);
    }

    @Transactional
    public void unfollowing(UserFollowingRequestDto userFollowingRequestDto){
        User user = userRepository.findById(userFollowingRequestDto.getUserId()).get();
        User unfollowingUser = userRepository.findById(userFollowingRequestDto.getFollowingUserId()).get();

        Follow follow = user.findFollowByUser(unfollowingUser);

        if (follow != null) {
            follow.deleteFollowing(user, unfollowingUser);
            followRepository.delete(follow);
        }
    }

    @Transactional
    public boolean isFollowingUser(Long userId, Long followingUserId){
        User user = userRepository.findById(userId).get();
        User followingUser = userRepository.findById(followingUserId).get();

        if (user.findFollowByUser(followingUser) == null){
            return false;
        }else{
            return true;
        }
    }
}
