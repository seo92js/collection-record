package side.collectionrecord.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.UserDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setRemoveAssertJRelatedElementsFromStackTrace;

@SpringBootTest
class UserApiControllerTest {
    private MockMvc mvc;

    @Autowired
    UserRepository userRepository;

    @Test
    public void 회원가입() throws Exception{
        //given
        UserDto userDto = UserDto.builder()
                .username("test")
                .password("1234")
                .email("test@naver.com")
                .profileImage(null)
                .build();

        String url = "http://localhost:8080/api/v1/users";

        assertThat(userDto).isNotNull();

        //when
        mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

/*        //then
        List<User> all = userRepository.findAll();
        User findUser = all.get(0);
        assertThat(findUser.getUsername()).isEqualTo(userDto.getUsername());*/
    }
}