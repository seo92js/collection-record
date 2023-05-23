package side.collectionrecord.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import side.collectionrecord.web.dto.UserLoginRequestDto;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginUserForm(Model model){
        model.addAttribute("userLoginRequestDto", new UserLoginRequestDto());
        return "loginForm";
    }
}
