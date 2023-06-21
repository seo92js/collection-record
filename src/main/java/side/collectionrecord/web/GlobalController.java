package side.collectionrecord.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalController {
    @ModelAttribute
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
    }
}
