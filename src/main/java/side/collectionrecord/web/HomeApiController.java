package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.config.auth.dto.SessionUser;
import side.collectionrecord.service.FollowService;
import side.collectionrecord.web.dto.GetFollowPostsResponseDto;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class HomeApiController {

    private final FollowService followService;

    @GetMapping("/api/v1/home/{page}")
    public List<GetFollowPostsResponseDto> getAllPostsFromFollowingUser(@PathVariable int page, HttpSession httpSession, Authentication authentication){

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (authentication != null && authentication.isAuthenticated()){
            return followService.getAllPostsByUserIdEqFollowingId(user.getId(), page, 5);
        } else {
            return null;
        }
    }
}
