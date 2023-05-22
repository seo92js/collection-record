package side.collectionrecord.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.UserJoinRequestDto;
import side.collectionrecord.web.dto.UserLoginRequestDto;
import side.collectionrecord.web.dto.UserUpdateRequestDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @Autowired
    private HttpServletRequest request;

    @AfterEach
    public void cleanup(){
        userRepository.deleteAll();
    }

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void 회원가입() throws Exception{
        //given
        UserJoinRequestDto userDto = UserJoinRequestDto.builder()
                .username("test")
                .password("1234")
                .build();

        String url = "http://localhost:" + port + "/api/v1/user-join";

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
        UserJoinRequestDto userDto = UserJoinRequestDto.builder()
                .username("test")
                .password("1234")
                .build();

        String url = "http://localhost:" + port + "/api/v1/user-join";

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
    public void 로그인() throws Exception{
        //given
        String username ="test";
        String password = "1234";
        //회원가입
        User user = User.builder()
                .username(username)
                .password(password)
                .image("Test")
                .build();

        userRepository.save(user);

        //로그인
        UserLoginRequestDto loginDto = UserLoginRequestDto.builder()
                .username(username)
                .password(password)
                .build();

        String url = "http://localhost:" + port + "/api/v1/user-login";

        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(loginDto)))
                        .andExpect(status().isOk());
    }

    @Test
    public void 로그아웃() throws  Exception{
        //회원가입
        UserJoinRequestDto userDto = UserJoinRequestDto.builder()
                .username("test")
                .password("1234")
                .build();

        String url = "http://localhost:" + port + "/api/v1/user-join";

        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(status().isOk());

        // 로그인
        UserLoginRequestDto loginDto = UserLoginRequestDto.builder()
                .username("test")
                .password("1234")
                .build();

        HttpSession session = mvc.perform(post("/api/v1/user-login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andReturn().getRequest().getSession(false);


        // 로그아웃
        session = mvc.perform(post("/api/v1/user-logout")
                        .session((MockHttpSession) session)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getRequest().getSession(false);

        assertThat(session).isNull();
    }

    @Test
    public void 정보수정() throws Exception{
        //given
        String expectedUsername = "2";
        String expectedPassword = "2";
        String expectedImage = "2";

        User user = User.builder()
                .username("1")
                .password("1")
                .image("1")
                .build();

        userRepository.save(user);

        UserUpdateRequestDto userUpdateRequestDto = UserUpdateRequestDto.builder()
                .username(expectedUsername)
                .password(expectedPassword)
                .image(expectedImage)
                .build();

        String url = "http://localhost:" + port + "/api/v1/user-update/" + user.getId();

        //when
        mvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userUpdateRequestDto)))
                .andExpect(status().isOk());

        //then
        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getUsername()).isEqualTo(expectedUsername);
        assertThat(all.get(0).getImage()).isEqualTo(expectedImage);
    }
}