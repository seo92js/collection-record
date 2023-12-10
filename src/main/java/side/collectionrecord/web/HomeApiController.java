package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.config.auth.dto.SessionUser;
import side.collectionrecord.service.FollowService;
import side.collectionrecord.web.dto.FollowPostsResponseDto;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class HomeApiController {

    private final FollowService followService;

    @GetMapping("/api/v1/home/{page}")
    public List<FollowPostsResponseDto> getAllPostsFromFollowingUser(@PathVariable int page, HttpSession httpSession, Authentication authentication){

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        PageRequest pageRequest = PageRequest.of(page, 5);

        if (authentication != null && authentication.isAuthenticated()){
            return followService.getAllPostsByUserIdEqFollowingId(user.getId(), pageRequest);
        } else {
            return null;
        }
    }
}
