package side.collectionrecord.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);

        if(session != null){
            model.addAttribute("login", true);
            model.addAttribute("username", session.getAttribute("username").toString());
        }else {
            model.addAttribute("login", false);
        }

        return "home";
    }
}
