package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.service.FollowService;
import side.collectionrecord.web.dto.CreateFollowRequestDto;

@RequiredArgsConstructor
@RestController
public class FollowApiController {

    private final FollowService followService;

    @PutMapping("/api/v1/follow/{id}")
    public Long createFollow(@PathVariable Long id, Model model){
        Long userId = (Long)model.getAttribute("loginUserId");

        followService.createFollow(CreateFollowRequestDto.builder()
                .userId(userId)
                .followingUserId(id)
                .build());

        return id;
    }

    @DeleteMapping("/api/v1/follow/{id}")
    public Long deleteFollow(@PathVariable Long id, Model model){
        Long userId = (Long)model.getAttribute("loginUserId");

        followService.deleteFollow(CreateFollowRequestDto.builder()
                .userId(userId)
                .followingUserId(id)
                .build());

        return id;
    }
}
