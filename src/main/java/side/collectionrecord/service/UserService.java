package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.UserJoinDto;
import side.collectionrecord.web.dto.UserLoginDto;
import side.collectionrecord.web.dto.UserProfileDto;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(UserJoinDto userJoinDto){

        validateDuplicateUser(userJoinDto.getEmail());

        return userRepository.save(User.builder()
                .username(userJoinDto.getUsername())
                .password(userJoinDto.getPassword())
                .email(userJoinDto.getEmail())
                .image(userJoinDto.getImage())
                .build()).getId();
    }

    @Transactional
    public User login(UserLoginDto userLoginDto){
        return userRepository.findByEmail(userLoginDto.getEmail()).filter(u -> u.getPassword().equals(userLoginDto.getPassword())
                ).orElse(null);
    }

    @Transactional
    public Long update(Long id, UserProfileDto userProfileDto){
        User findUser = userRepository.findById(id).orElse(null);
        findUser.update(userProfileDto.getUsername(), userProfileDto.getImage());

        return id;
    }

    private void validateDuplicateUser(String email){
        Optional<User> findUser = userRepository.findByEmail(email);

        if(!findUser.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
