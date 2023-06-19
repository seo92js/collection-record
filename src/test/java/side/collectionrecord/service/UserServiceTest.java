package side.collectionrecord.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.UserJoinRequestDto;
import side.collectionrecord.web.dto.UserProfileResponseDto;
import side.collectionrecord.web.dto.UserUpdateRequestDto;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ImageRepository imageRepository;

    @Test
    public void 유저_회원가입() throws IOException {
        //given
        UserJoinRequestDto userJoinRequestDto = UserJoinRequestDto.builder()
                .username("test")
                .password("test")
                .build();

        Long id = userService.join(userJoinRequestDto);

        //when
        User findUser = userRepository.findById(id).orElse(null);

        //then
        assertThat(findUser.getUsername()).isEqualTo(userJoinRequestDto.getUsername());
        assertThat(findUser.getPassword()).isEqualTo(userJoinRequestDto.getPassword());
    }

    @Test
    public void 유저_업데이트() throws IOException {
        //given
        User user = User.builder()
                .username("test")
                .password("test")
                .profileImage(null)
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

        userService.update(user.getId(), userUpdateRequestDto);

        //when
        User findUser = userRepository.findAll().get(0);

        //then
        assertThat(findUser.getUsername()).isEqualTo(expectedName);
        assertThat(findUser.getProfileImage().getFilename()).isEqualTo("expectedImage");
    }

    @Test
    public void 유저이름으로_찾기(){
        //given
        User user = User.builder()
                .username("test")
                .password("test")
                .profileImage(null)
                .build();

        userRepository.save(user);

        //when
        UserProfileResponseDto userProfileResponseDto = userService.findById(user.getId());

        //then
        assertThat(userProfileResponseDto.getUsername()).isEqualTo(user.getUsername());
        assertThat(userProfileResponseDto.getProfileImage()).isEqualTo(user.getProfileImage());
    }
}