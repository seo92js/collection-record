package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.service.FollowService;
import side.collectionrecord.web.dto.FollowPostsListResponseDto;
import side.collectionrecord.web.dto.PostsListResponseDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class HomeApiController {

    private final FollowService followService;

    @GetMapping("/api/v1/home/{page}")
    public List<FollowPostsListResponseDto> findFollowPosts(@PathVariable int page, Model model){

        boolean login = (Boolean) model.getAttribute("login");

        if(login == true){
            return followService.findFollowPosts((Long) model.getAttribute("loginUserId"), page, 5);
        } else {
            return null;
        }
    }
}
