package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.UserFollowingRequestDto;
import side.collectionrecord.web.dto.UserJoinRequestDto;
import side.collectionrecord.web.dto.UserProfileResponseDto;
import side.collectionrecord.web.dto.UserUpdateRequestDto;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(UserJoinRequestDto userJoinRequestDto){

        validateDuplicateUser(userJoinRequestDto.getUsername());

        return userRepository.save(User.builder()
                .username(userJoinRequestDto.getUsername())
                .password(userJoinRequestDto.getPassword())
                .image(null)
                .build()).getId();
    }

    @Transactional
    public Long update(Long id, UserUpdateRequestDto userUpdateRequestDto){
        User findUser = userRepository.findById(id).orElse(null);
        findUser.update(userUpdateRequestDto.getUsername(), userUpdateRequestDto.getImage());

        return id;
    }

    @Transactional
    public UserProfileResponseDto findById (Long id){
        User user = userRepository.findById(id).orElse(null);

        return new UserProfileResponseDto(user);
    }

    @Transactional
    public void following(UserFollowingRequestDto userFollowingRequestDto){
        User user = userRepository.findById(userFollowingRequestDto.getUserId()).get();
        User followingUser = userRepository.findById(userFollowingRequestDto.getFollowingUserId()).get();

        user.addFollowing(followingUser);
    }

    @Transactional
    public void unfollowing(UserFollowingRequestDto userFollowingRequestDto){
        User user = userRepository.findById(userFollowingRequestDto.getUserId()).get();
        User unfollowingUser = userRepository.findById(userFollowingRequestDto.getFollowingUserId()).get();

        user.deleteFollowing(unfollowingUser);
    }

    private void validateDuplicateUser(String username){
        Optional<User> findUser = userRepository.findByUsername(username);

        if(!findUser.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
