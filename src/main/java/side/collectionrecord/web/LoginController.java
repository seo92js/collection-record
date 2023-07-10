package side.collectionrecord.web;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import side.collectionrecord.web.dto.LoginRequestDto;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginUserForm(Model model){
        model.addAttribute("loginRequestDto", new LoginRequestDto());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken)
            return "loginForm";

        return "redirect:/";
    }
}
