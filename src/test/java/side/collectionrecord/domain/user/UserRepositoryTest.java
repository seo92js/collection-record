package side.collectionrecord.domain.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
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
        String image = "ff";

        userRepository.save(User.builder()
                        .username(username)
                        .email(email)
                        .password(password)
                        .image(image)
                .build());

        //when
        List<User> findUsers = userRepository.findAll();

        //then
        User findUser = findUsers.get(0);
        assertThat(findUser.getUsername()).isEqualTo(username);
        assertThat(findUser.getEmail()).isEqualTo(email);
        assertThat(findUser.getPassword()).isEqualTo(password);
        assertThat(findUser.getImage()).isEqualTo(image);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2022,5,16,0,0,0);
        userRepository.save(User.builder()
                        .username("test")
                        .password("test")
                        .email("test")
                        .image("test")
                        .build());
        //when
        List<User> all = userRepository.findAll();
        User findUser = all.get(0);

        //then
        System.out.println("createDate = " + findUser.getCreatedDate() + " , modifiedDate = " + findUser.getModifiedDate());
        assertThat(findUser.getCreatedDate()).isAfter(now);
        assertThat(findUser.getModifiedDate()).isAfter(now);
    }
}