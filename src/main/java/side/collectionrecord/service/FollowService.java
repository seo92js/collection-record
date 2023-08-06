package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.follow.Follow;
import side.collectionrecord.domain.follow.FollowRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.CreateFollowRequestDto;
import side.collectionrecord.web.dto.GetFollowPostsResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FollowService {
    private final UserRepository userRepository;

    private final FollowRepository followRepository;

    @Transactional
    public void createFollow(CreateFollowRequestDto createFollowRequestDto){
        User user = userRepository.findById(createFollowRequestDto.getUserId()).get();
        User followingUser = userRepository.findById(createFollowRequestDto.getFollowingUserId()).get();

        Follow follow = Follow.builder()
                .following(user)
                .follower(followingUser)
                .build();

        followRepository.save(follow);
    }

    @Transactional
    public void deleteFollow(CreateFollowRequestDto createFollowRequestDto){
        User user = userRepository.findById(createFollowRequestDto.getUserId()).get();
        User unfollowingUser = userRepository.findById(createFollowRequestDto.getFollowingUserId()).get();

        Follow follow = user.getFollowByUser(unfollowingUser);

        if (follow != null) {
            follow.deleteFollowing(user, unfollowingUser);
            followRepository.delete(follow);
        }
    }

    @Transactional
    public boolean checkFollow(Long userId, Long followingUserId){
        User user = userRepository.findById(userId).get();
        User followingUser = userRepository.findById(followingUserId).get();

        if (user.getFollowByUser(followingUser) == null){
            return false;
        }else{
            return true;
        }
    }

    @Transactional
    public List<GetFollowPostsResponseDto> getAllPostsByUserIdEqFollowingId(Long userId, int page, int size){

        int offset = page * size;

        return followRepository.findPostsByUserIdEqFollowingId(userId, offset, size).stream()
                .map(GetFollowPostsResponseDto::new)
                .collect(Collectors.toList());
    }
}
