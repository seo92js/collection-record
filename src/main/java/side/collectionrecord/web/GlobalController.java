package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalController {
    private final UserRepository userRepository;
/*    @ModelAttribute
    public void addUserAttributes(Model model, HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession(false);

        if (httpSession != null){
            model.addAttribute("session", httpSession);
            model.addAttribute("login", true);

            Long loginUserId = (Long) httpSession.getAttribute("loginUserId");
            String loginUsername = (String) httpSession.getAttribute("loginUsername");

            if (loginUserId != null){
                model.addAttribute("loginUserId", loginUserId);
                model.addAttribute("loginUsername", loginUsername);
            }
        } else {
            model.addAttribute("login", false);
        }
    }*/

    @ModelAttribute
    public void addUserAttributes(Model model, Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()){
/*            model.addAttribute("session", httpSession);
            model.addAttribute("login", true);

            Long loginUserId = (Long) httpSession.getAttribute("loginUserId");
            String loginUsername = (String) httpSession.getAttribute("loginUsername");

            if (loginUserId != null){
                model.addAttribute("loginUserId", loginUserId);
                model.addAttribute("loginUsername", loginUsername);
            }*/

            User user = userRepository.findByUsername(authentication.getName()).orElse(null);

            model.addAttribute("loginUserId", user.getId());
            model.addAttribute("loginUsername", authentication.getName());

        } else {
            //model.addAttribute("login", false);
        }
    }
}
