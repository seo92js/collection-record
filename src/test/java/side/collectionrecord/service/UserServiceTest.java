package side.collectionrecord.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.domain.user.Role;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.SearchUserResponseDto;
import side.collectionrecord.web.dto.UserProfileForm;

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

    @Test
    public void 유저_업데이트() throws IOException {
        //given
        User user = User.builder()
                .username("test")
                .profileImage(null)
                .profileText(null)
                .email("email")
                .role(Role.USER)
                .build();

        userRepository.save(user);

        String expectedName = "test2";

        Image image = Image.builder()
                .filename("expectedImage")
                .data(null)
                .build();

        imageRepository.save(image);

        UserProfileForm userProfileForm = UserProfileForm.builder()
                .username(expectedName)
                .profileImage(null)
                .profileText("text")
                .build();

        //userUpdateRequestDto.encodePassword(passwordEncoder);

        userService.userUpdate(user.getId(), userProfileForm, image);

        //when
        User findUser = userRepository.findAll().get(0);

        //then
        assertThat(findUser.getUsername()).isEqualTo(expectedName);
        //assertThat(passwordEncoder.matches(findUser.getPassword(), userUpdateRequestDto.getPassword()));
        assertThat(findUser.getProfileImage().getFilename()).isEqualTo("expectedImage");
    }

    @Test
    public void 유저이름으로_찾기(){
        //given
        User user = User.builder()
                .username("test")
                .profileImage(null)
                .profileText(null)
                .email("email")
                .role(Role.USER)
                .build();

        userRepository.save(user);

        //when
        UserProfileForm userProfileForm = userService.findById(user.getId());

        //then
        assertThat(userProfileForm.getUsername()).isEqualTo(user.getUsername());
        assertThat(userProfileForm.getProfileImage()).isEqualTo(user.getProfileImage());
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
                .profileImage(image)
                .profileText(null)
                .email("email")
                .role(Role.USER)
                .build();

        userRepository.save(user1);

        User user2 = User.builder()
                .username("김수미")
                .role(Role.USER)
                .profileImage(image)
                .profileText(null)
                .email("email")
                .build();

        userRepository.save(user2);

        User user3 = User.builder()
                .username("김구라")
                .role(Role.USER)
                .profileImage(image)
                .profileText(null)
                .email("email")
                .build();

        userRepository.save(user3);

        PageRequest pageRequest = PageRequest.of(0, 5);
        //when
        List<SearchUserResponseDto> containsUsername = userService.getAllUserByUsernameContains(username, pageRequest);

        //then
        assertThat(containsUsername.size()).isEqualTo(3);
    }
}