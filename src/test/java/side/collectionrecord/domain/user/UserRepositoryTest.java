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
        String password = "ff";

        userRepository.save(User.builder()
                        .username(username)
                        .password(password)
                        .profileImage(null)
                .build());

        //when
        List<User> findUsers = userRepository.findAll();

        //then
        User findUser = findUsers.get(0);
        assertThat(findUser.getUsername()).isEqualTo(username);
        assertThat(findUser.getPassword()).isEqualTo(password);
        assertThat(findUser.getProfileImage()).isNull();
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2022,5,16,0,0,0);

        userRepository.save(User.builder()
                        .username("test")
                        .password("test")
                        .profileImage(null)
                        .build());
        //when
        List<User> all = userRepository.findAll();
        User findUser = all.get(0);

        //then
        System.out.println("createDate = " + findUser.getCreatedDate() + " , modifiedDate = " + findUser.getModifiedDate());
        assertThat(findUser.getCreatedDate()).isAfter(now);
        assertThat(findUser.getModifiedDate()).isAfter(now);
    }

    @Test
    public void 이름으로_찾기(){
        //given
        User user = User.builder()
                .username("user1")
                .password("1234")
                .profileImage(null)
                .build();

        userRepository.save(user);

        //when
        User findUser = userRepository.findByUsername("user1").get();

        //then
        assertThat(findUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(findUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(findUser.getProfileImage()).isNull();
    }
}