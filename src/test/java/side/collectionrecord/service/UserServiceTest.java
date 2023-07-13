package side.collectionrecord.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.domain.user.UserRole;
import side.collectionrecord.web.dto.UserJoinRequestDto;
import side.collectionrecord.web.dto.UserProfileResponseDto;
import side.collectionrecord.web.dto.UserSearchResponseDto;
import side.collectionrecord.web.dto.UserUpdateRequestDto;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void 유저_회원가입() throws IOException {
        //given
        UserJoinRequestDto userJoinRequestDto = UserJoinRequestDto.builder()
                .username("test")
                .password("test")
                .build();

        userJoinRequestDto.encodePassword(passwordEncoder);

        Long id = userService.join(userJoinRequestDto);

        //when
        User findUser = userRepository.findById(id).orElse(null);

        //then
        assertThat(findUser.getUsername()).isEqualTo(userJoinRequestDto.getUsername());
        //assertThat(findUser.getPassword()).isEqualTo(passwordEncoder.encode(userJoinRequestDto.getPassword()));
        assertThat(passwordEncoder.matches(findUser.getPassword(), userJoinRequestDto.getPassword()));
    }

    @Test
    public void 유저_업데이트() throws IOException {
        //given
        User user = User.builder()
                .username("test")
                .password("test")
                .profileImage(null)
                .userRole(UserRole.USER)
                .build();

        userRepository.save(user);

        String expectedName = "test2";

        Image image = Image.builder()
                .filename("expectedImage")
                .data(null)
                .build();

        imageRepository.save(image);

        UserUpdateRequestDto userUpdateRequestDto = UserUpdateRequestDto.builder()
                .username(expectedName)
                .password("test")
                .profileImage(image)
                .build();

        userUpdateRequestDto.encodePassword(passwordEncoder);

        userService.update(user.getId(), userUpdateRequestDto);

        //when
        User findUser = userRepository.findAll().get(0);

        //then
        assertThat(findUser.getUsername()).isEqualTo(expectedName);
        assertThat(passwordEncoder.matches(findUser.getPassword(), userUpdateRequestDto.getPassword()));
        assertThat(findUser.getProfileImage().getFilename()).isEqualTo("expectedImage");
    }

    @Test
    public void 유저이름으로_찾기(){
        //given
        User user = User.builder()
                .username("test")
                .password("test")
                .profileImage(null)
                .userRole(UserRole.USER)

                .build();

        userRepository.save(user);

        //when
        UserProfileResponseDto userProfileResponseDto = userService.findById(user.getId());

        //then
        assertThat(userProfileResponseDto.getUsername()).isEqualTo(user.getUsername());
        assertThat(userProfileResponseDto.getProfileImage()).isEqualTo(user.getProfileImage());
    }

    @Test
    public void 이름이_포함된_모든유저_검색() throws IOException {
        //given
        String username = "김";

        Image image = Image.builder()
                .filename("image")
                .data(null)
                .build();

        imageRepository.save(image);

        User user1 = User.builder()
                .username("김김김")
                .password("1")
                .profileImage(image)
                .userRole(UserRole.USER)
                .build();

        userRepository.save(user1);

        User user2 = User.builder()
                .username("김수미")
                .password("1")
                .userRole(UserRole.USER)
                .profileImage(image)
                .build();

        userRepository.save(user2);

        User user3 = User.builder()
                .username("김구라")
                .password("1")
                .userRole(UserRole.USER)
                .profileImage(image)
                .build();

        userRepository.save(user3);

        //when
        List<UserSearchResponseDto> containsUsername = userService.findContainsUsername(username, 0, 5);

        //then
        assertThat(containsUsername.size()).isEqualTo(3);
    }
}