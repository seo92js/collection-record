package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.UserJoinDto;
import side.collectionrecord.web.dto.UserLoginDto;

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
    public User login(UserLoginDto userLoginDto){
        return userRepository.findByEmail(userLoginDto.getEmail()).filter(u -> u.getPassword().equals(userLoginDto.getPassword())
                ).orElse(null);
    }
}
