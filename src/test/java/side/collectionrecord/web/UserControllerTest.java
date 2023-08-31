package side.collectionrecord.web;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import side.collectionrecord.config.auth.SecurityConfig;
import side.collectionrecord.domain.user.Role;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.service.FollowService;
import side.collectionrecord.service.UserChatRoomService;
import side.collectionrecord.service.UserService;

import java.util.Optional;

@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(controllers = UserController.class, excludeFilters = {
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
},
        excludeAutoConfiguration = SecurityConfig.class
)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private FollowService followService;

    @MockBean
    private UserChatRoomService userChatRoomService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private Model model;

    @BeforeEach
    public void setup(){
        User user = User.builder()
                .username("user")
                .profileText(null)
                .profileImage(null)
                .role(Role.USER)
                .build();

        Mockito.when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));
    }
}