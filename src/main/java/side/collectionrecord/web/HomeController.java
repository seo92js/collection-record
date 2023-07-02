package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import side.collectionrecord.service.FollowService;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final FollowService followService;

    @RequestMapping("/")
    public String home(Model model){

        return "home";
    }
}
