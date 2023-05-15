package side.collectionrecord.domain.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void cleanup(){
        userRepository.deleteAll();
    }

    @Test
    public void save(){
        //given
        String username = "seo";
        String email = "test@test.com";
        String password = "ff";
        String profile_image = "ff";

        userRepository.save(User.builder()
                        .username(username)
                        .email(email)
                        .password(password)
                        .profile_image(null)
                .build());

        //when
        List<User> findUsers = userRepository.findAll();

        //then
        User findUser = findUsers.get(0);
        assertThat(findUser.getUsername()).isEqualTo(username);
        assertThat(findUser.getEmail()).isEqualTo(email);
        assertThat(findUser.getPassword()).isEqualTo(password);
        //assertThat(findUser.getProfile_image()).isEqualTo(profile_image);
    }
}