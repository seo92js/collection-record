package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalController {
    private final UserRepository userRepository;

    @ModelAttribute
    public void addUserAttributes(Model model, Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()){
            User user = userRepository.findByUsername(authentication.getName()).orElse(null);

            model.addAttribute("loginUserId", user.getId());
            model.addAttribute("loginUsername", authentication.getName());
        }
    }
}
