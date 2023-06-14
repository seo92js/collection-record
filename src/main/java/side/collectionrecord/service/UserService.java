package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .profileImage(null)
                .build()).getId();
    }

    @Transactional
    public Long update(Long id, UserUpdateRequestDto userUpdateRequestDto) throws IOException {
        User findUser = userRepository.findById(id).orElse(null);
        findUser.update(userUpdateRequestDto.getUsername(), userUpdateRequestDto.getProfileImage());

        return id;
    }

    @Transactional
    public UserProfileResponseDto findById (Long id){
        User user = userRepository.findById(id).orElse(null);

        return new UserProfileResponseDto(user);
    }

    @Transactional
    public List<UserSearchResponseDto> findContainsUsername(String username){
        return userRepository.findContainsUsername(username).stream()
                .map(UserSearchResponseDto::new)
                .collect(Collectors.toList());
    }

    private void validateDuplicateUser(String username){
        Optional<User> findUser = userRepository.findByUsername(username);

        if(!findUser.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
