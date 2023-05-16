package side.collectionrecord.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.UserJoinDto;
import side.collectionrecord.web.dto.UserUpdateDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void 회원가입() throws Exception{
        //given
        UserJoinDto userDto = UserJoinDto.builder()
                .username("test")
                .password("1234")
                .email("test@naver.com")
                .profile("Test")
                .build();

        String url = "http://localhost:" + port + "/api/v1/users";

        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, userDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<User> all = userRepository.findAll();
        User findUser = all.get(0);
        assertThat(findUser.getUsername()).isEqualTo(userDto.getUsername());
    }

    @Test
    public void 회원가입2() throws Exception{
        //given
        UserJoinDto userDto = UserJoinDto.builder()
                .username("test")
                .password("1234")
                .email("test@naver.com")
                .profile("Test")
                .build();

        String url = "http://localhost:" + port + "/api/v1/users";

        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(status().isOk());

        //then
        List<User> all = userRepository.findAll();
        User findUser = all.get(0);
        assertThat(findUser.getUsername()).isEqualTo(userDto.getUsername());
    }

    @Test
    public void 정보수정() throws Exception{
        //given
        User savedUser = userRepository.save(User.builder()
                .username("1")
                .password("1")
                .email("1")
                .profile("1")
                .build());

        Long savedId = savedUser.getId();

        String updateUsername = "2";
        String updateProfile = "2";

        UserUpdateDto userUpdateDto = UserUpdateDto.builder()
                .username(updateUsername)
                .profile(updateProfile)
                .build();

        String url = "http://localhost:" + port + "/api/v1/users/" + savedId;

        HttpEntity<UserUpdateDto> userUpdateDtoHttpEntity = new HttpEntity<>(userUpdateDto);

        //when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userUpdateDto)))
                .andExpect(status().isOk());
        //then

        List<User> all = userRepository.findAll();
        User findUser = all.get(0);
        assertThat(findUser.getUsername()).isEqualTo(updateUsername);
        assertThat(findUser.getProfile()).isEqualTo(updateProfile);
    }
}