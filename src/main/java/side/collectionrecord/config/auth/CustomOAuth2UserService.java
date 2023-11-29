package side.collectionrecord.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import side.collectionrecord.config.auth.dto.OAuthAttributes;
import side.collectionrecord.config.auth.dto.SessionUser;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest
                                        .getClientRegistration()
                                        .getProviderDetails()
                                        .getUserInfoEndpoint()
                                        .getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

//        User user = userRepository.findByEmail(attributes.getEmail()).orElse(null);
//
//        //유저가 없으면 save 하고,
//        if (user == null){
//            try {
//                user = save(attributes);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        } /*else {
//            //소셜에서 업데이트 된 내용이 있을 수 있으니.
//            user = update(attributes);
//        }*/
//
//        httpSession.setAttribute("user", new SessionUser(user));

        User user = userRepository.findByEmail(attributes.getEmail())
                .orElseGet(() -> {
                    try {
                        return save(attributes);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User save(OAuthAttributes attributes) throws IOException {
        byte[] data = getClass().getResourceAsStream("/static/img/default.jpg").readAllBytes();

        Image image = Image.builder()
                .filename("default")
                .data(data)
                .build();

        imageRepository.save(image);

        User user = attributes.toEntity();

        String[] parts = attributes.getEmail().split("@");
        String username = removeSpecialCharacters(parts[0]) + removeSpecialCharacters(parts[1]);

        user.update(username, image, null);

        return userRepository.save(user);
    }

    public static String removeSpecialCharacters(String input) {
        return input.replaceAll("[^a-z0-9]", "");
    }

/*    private User update(OAuthAttributes attributes) {

        User user = userRepository.findByEmail(attributes.getEmail()).orElse(attributes.toEntity());

        user.update(attributes.getName(), user.getProfileImage(), user.getProfileText());

        return user;
    }*/
}
