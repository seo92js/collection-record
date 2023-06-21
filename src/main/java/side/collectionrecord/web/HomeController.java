package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import side.collectionrecord.service.FollowService;
import side.collectionrecord.web.dto.FollowPostsListResponseDto;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final FollowService followService;

    @RequestMapping("/")
    public String home(Model model){
        boolean login = (Boolean) model.getAttribute("login");

        if(login == true){
            List<FollowPostsListResponseDto> followPostsListResponseDtos = followService.findFollowPosts((Long)model.getAttribute("loginUserId"));
            model.addAttribute("posts", followPostsListResponseDtos);
        }

        return "home";
    }
}
