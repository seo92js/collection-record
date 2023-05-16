package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.UserJoinDto;
import side.collectionrecord.web.dto.UserUpdateDto;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(UserJoinDto userJoinDto){
        return userRepository.save(User.builder()
                .username(userJoinDto.getUsername())
                .password(userJoinDto.getPassword())
                .email(userJoinDto.getEmail())
                .profile(userJoinDto.getProfile())
                .build()).getId();
    }

    @Transactional
    public Long update(Long id, UserUpdateDto userUpdateDto){
        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저가 없습니다. id=" + id));

        findUser.update(
                userUpdateDto.getUsername(),
                userUpdateDto.getProfile()
        );

        return id;
    }
}
