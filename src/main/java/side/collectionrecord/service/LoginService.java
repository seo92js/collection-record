package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.web.dto.LoginRequestDto;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final UserRepository userRepository;

    @Transactional
    public User login(LoginRequestDto loginRequestDto){
        return userRepository.findByUsername(loginRequestDto.getUsername()).filter(u -> u.getPassword().equals(loginRequestDto.getPassword())
        ).orElse(null);
    }
}
