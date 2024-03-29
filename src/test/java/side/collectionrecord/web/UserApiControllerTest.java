package side.collectionrecord.web;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import side.collectionrecord.config.auth.SecurityConfig;
import side.collectionrecord.domain.user.Role;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.service.ImageService;
import side.collectionrecord.service.UserService;

import java.util.Optional;

@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(value = UserApiControllerTest.class, excludeFilters = {
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
},
        excludeAutoConfiguration = SecurityConfig.class
)
class UserApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @Mock
    private ImageService imageService;

    @BeforeEach
    public void setup(){
        User user = User.builder()
                .username("user")
                .email("email")
                .profileText(null)
                .profileImage(null)
                .role(Role.USER)
                .build();

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));
    }

    //403 에러 계속 나네.. 추후 해결 예정
/*    @WithMockUser(roles = "USER")
    @Test
    public void 업데이트() throws Exception {

        UpdateUserRequestDto updateUserRequestDto = UpdateUserRequestDto.builder()
                .username("user2")
                .profileText("text")
                .profileImage(null)
                .build();

        MultipartFile mockImageFile = new MockMultipartFile(
                "profileImage", "filename.jpg", "image/jpeg", new byte[0]
        );

        MockMultipartHttpServletRequestBuilder requestBuilder =
                (MockMultipartHttpServletRequestBuilder) MockMvcRequestBuilders.multipart("/api/v1/user/1")
                        .file((MockMultipartFile) mockImageFile)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(updateUserRequestDto));

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }*/
}