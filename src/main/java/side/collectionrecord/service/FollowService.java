package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.follow.Follow;
import side.collectionrecord.domain.follow.FollowRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.exception.UserNotFoundException;
import side.collectionrecord.web.dto.FollowPostsResponseDto;
import side.collectionrecord.web.dto.FollowRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FollowService {
    private final UserRepository userRepository;

    private final FollowRepository followRepository;

    @Transactional
    public void createFollow(FollowRequestDto followRequestDto){
        User user = userRepository.findById(followRequestDto.getUserId()).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));
        User followingUser = userRepository.findById(followRequestDto.getFollowingUserId()).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));

        Follow follow = Follow.builder()
                .following(user)
                .follower(followingUser)
                .build();

        followRepository.save(follow);
    }

    @Transactional
    public void deleteFollow(FollowRequestDto followRequestDto){
        User user = userRepository.findById(followRequestDto.getUserId()).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));
        User unfollowingUser = userRepository.findById(followRequestDto.getFollowingUserId()).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));

        Follow follow = user.getFollowByUser(unfollowingUser);

        if (follow != null) {
            follow.deleteFollowing(user, unfollowingUser);
            followRepository.delete(follow);
        }
    }

    @Transactional
    public boolean checkFollow(Long userId, Long followingUserId){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));
        User followingUser = userRepository.findById(followingUserId).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));

        if (user.getFollowByUser(followingUser) == null){
            return false;
        }else{
            return true;
        }
    }

    @Transactional
    public List<FollowPostsResponseDto> getAllPostsByUserIdEqFollowingId(Long userId, Pageable pageable){

        return followRepository.findPostsByUserIdEqFollowingId(userId, pageable).stream()
                .map(FollowPostsResponseDto::new)
                .collect(Collectors.toList());
    }
}
