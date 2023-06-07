package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.service.FollowService;
import side.collectionrecord.web.dto.UserFollowingRequestDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class FollowApiController {

    private final FollowService followService;

    @PutMapping("/api/v1/following/{id}")
    public Long following(@PathVariable Long id, HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();

        Long userId = (Long)httpSession.getAttribute("userId");

        followService.following(UserFollowingRequestDto.builder()
                .userId(userId)
                .followingUserId(id)
                .build());

        return id;
    }

    @PutMapping("/api/v1/unfollowing/{id}")
    public Long unfollowing(@PathVariable Long id, HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();

        Long userId = (Long)httpSession.getAttribute("userId");

        followService.unfollowing(UserFollowingRequestDto.builder()
                .userId(userId)
                .followingUserId(id)
                .build());

        return id;
    }
}
