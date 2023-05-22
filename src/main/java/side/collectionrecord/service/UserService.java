package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.UserJoinRequestDto;
import side.collectionrecord.web.dto.UserLoginRequestDto;
import side.collectionrecord.web.dto.UserProfileResponseDto;
import side.collectionrecord.web.dto.UserUpdateRequestDto;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(UserJoinRequestDto userJoinRequestDto){

        validateDuplicateUser(userJoinRequestDto.getEmail());

        return userRepository.save(User.builder()
                .username(userJoinRequestDto.getUsername())
                .password(userJoinRequestDto.getPassword())
                .email(userJoinRequestDto.getEmail())
                .image(userJoinRequestDto.getImage())
                .build()).getId();
    }

    @Transactional
    public User login(UserLoginRequestDto userLoginRequestDto){
        return userRepository.findByEmail(userLoginRequestDto.getEmail()).filter(u -> u.getPassword().equals(userLoginRequestDto.getPassword())
                ).orElse(null);
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

    private void validateDuplicateUser(String email){
        Optional<User> findUser = userRepository.findByEmail(email);

        if(!findUser.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
