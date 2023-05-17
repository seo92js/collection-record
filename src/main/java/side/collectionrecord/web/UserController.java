package side.collectionrecord.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import side.collectionrecord.web.dto.UserJoinDto;
import side.collectionrecord.web.dto.UserLoginDto;

@Controller
public class UserController {
    @GetMapping("/users/join")
    public String joinUserForm(Model model){
        model.addAttribute("userJoinDto", new UserJoinDto());
        return "users/userJoinForm";
    }

    @GetMapping("/users/login")
    public String loginUserForm(Model model){
        model.addAttribute("userLoginDto", new UserLoginDto());
        return "users/userLoginForm";
    }
}
