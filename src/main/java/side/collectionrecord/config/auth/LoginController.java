package side.collectionrecord.config.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import side.collectionrecord.config.auth.dto.LoginRequestDto;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginUserForm(Model model){
        model.addAttribute("loginRequestDto", new LoginRequestDto());
        return "loginForm";
    }
}
