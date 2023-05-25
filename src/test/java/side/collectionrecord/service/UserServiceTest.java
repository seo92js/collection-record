package side.collectionrecord.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.UserJoinRequestDto;
import side.collectionrecord.web.dto.UserProfileResponseDto;
import side.collectionrecord.web.dto.UserUpdateRequestDto;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void 유저_회원가입(){
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
    public void 유저_업데이트(){
        //given
        User user = User.builder()
                .username("test")
                .password("test")
                .image(null)
                .build();

        userRepository.save(user);

        String expectedName = "test2";
        String expectedImage = "test2";

        UserUpdateRequestDto userUpdateRequestDto = UserUpdateRequestDto.builder()
                .username(expectedName)
                .password("test")
                .image(expectedImage)
                .build();

        userService.update(user.getId(), userUpdateRequestDto);

        //when
        User findUser = userRepository.findAll().get(0);

        //then
        assertThat(findUser.getUsername()).isEqualTo(expectedName);
        assertThat(findUser.getImage()).isEqualTo(expectedImage);
    }

    @Test
    public void 유저이름으로_찾기(){
        //given
        User user = User.builder()
                .username("test")
                .password("test")
                .image(null)
                .build();

        userRepository.save(user);

        //when
        UserProfileResponseDto userProfileResponseDto = userService.findById(user.getId());

        //then
        assertThat(userProfileResponseDto.getUsername()).isEqualTo(user.getUsername());
        assertThat(userProfileResponseDto.getImage()).isEqualTo(user.getImage());
    }
}