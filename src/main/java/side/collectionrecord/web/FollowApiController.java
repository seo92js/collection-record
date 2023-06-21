package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.service.FollowService;
import side.collectionrecord.web.dto.UserFollowingRequestDto;

@RequiredArgsConstructor
@RestController
public class FollowApiController {

    private final FollowService followService;

    @PutMapping("/api/v1/following/{id}")
    public Long following(@PathVariable Long id, Model model){
        Long userId = (Long)model.getAttribute("loginUserId");

        followService.following(UserFollowingRequestDto.builder()
                .userId(userId)
                .followingUserId(id)
                .build());

        return id;
    }

    @PutMapping("/api/v1/unfollowing/{id}")
    public Long unfollowing(@PathVariable Long id, Model model){
        Long userId = (Long)model.getAttribute("loginUserId");

        followService.unfollowing(UserFollowingRequestDto.builder()
                .userId(userId)
                .followingUserId(id)
                .build());

        return id;
    }
}
