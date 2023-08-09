package side.collectionrecord.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import side.collectionrecord.config.auth.SecurityConfig;
import side.collectionrecord.domain.user.UserRepository;

@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(controllers = LoginControllerTest.class, excludeFilters = {
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void 로그인_폼() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}