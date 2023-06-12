package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import side.collectionrecord.service.FollowService;
import side.collectionrecord.web.dto.FollowPostsListResponseDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final FollowService followService;

    @RequestMapping("/")
    public String home(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);

        if(session != null){
            model.addAttribute("login", true);
            //model.addAttribute("userId", (Long)session.getAttribute("userId"));
            model.addAttribute("username", session.getAttribute("username").toString());

            List<FollowPostsListResponseDto> followPostsListResponseDtos = followService.findFollowPosts((Long)session.getAttribute("userId"));

            model.addAttribute("posts", followPostsListResponseDtos);
        }else {
            model.addAttribute("login", false);
        }

        return "home";
    }
}
