package side.collectionrecord.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;

@RequiredArgsConstructor
@Component
public class AuthProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        PasswordEncoder passwordEncoder = null;

        UsernamePasswordAuthenticationToken token;

        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null && passwordEncoder.matches(password, user.getPassword())){
            token = new UsernamePasswordAuthenticationToken(user.getId(), null);

            return token;
        }

        throw new BadCredentialsException("No such user or wrong password.");
        // Exception을 던지지 않고 다른 값을 반환하면 authenticate() 메서드는 정상적으로 실행된 것이므로 인증되지 않았다면 Exception을 throw 해야 한다.
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
