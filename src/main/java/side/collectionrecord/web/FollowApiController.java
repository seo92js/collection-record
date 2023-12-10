package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.config.auth.dto.SessionUser;
import side.collectionrecord.service.FollowService;
import side.collectionrecord.web.dto.FollowRequestDto;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class FollowApiController {

    private final FollowService followService;

    @PutMapping("/api/v1/follow/{id}")
    public Long createFollow(@PathVariable Long id, HttpSession httpSession){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        followService.createFollow(FollowRequestDto.builder()
                .userId(user.getId())
                .followingUserId(id)
                .build());

        return id;
    }

    @DeleteMapping("/api/v1/follow/{id}")
    public Long deleteFollow(@PathVariable Long id, HttpSession httpSession){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        followService.deleteFollow(FollowRequestDto.builder()
                .userId(user.getId())
                .followingUserId(id)
                .build());

        return id;
    }
}
