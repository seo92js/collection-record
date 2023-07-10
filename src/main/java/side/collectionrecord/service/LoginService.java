package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
/*        User user = userRepository.findByUsername(loginRequestDto.getUsername()).orElse(null);

        if (user != null && passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            return user;
        }

        return null;*/

/*        // 사용자 인증을 위해 Authentication 객체 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginRequestDto.getUsername(),
                loginRequestDto.getPassword()
        );

        try {
            // AuthenticationManager를 통해 사용자 인증 수행
            Authentication authenticated = authenticationManager.authenticate(authentication);

            // 사용자 인증에 성공한 경우
            if (authenticated.isAuthenticated()) {
                // 인증된 사용자의 정보를 가져옴
                String username = authenticated.getName();
                User user = userRepository.findByUsername(username).orElse(null);

                return user;
            }
        } catch (AuthenticationException e){
            return null;
        }*/

        return null;
    }
}
