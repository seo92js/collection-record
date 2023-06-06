package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import side.collectionrecord.service.UserService;
import side.collectionrecord.web.dto.UserFollowingRequestDto;
import side.collectionrecord.web.dto.UserJoinRequestDto;
import side.collectionrecord.web.dto.UserUpdateRequestDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/api/v1/user-join")
    public Long save (@RequestBody UserJoinRequestDto userJoinRequestDto){
        Long id = userService.join(userJoinRequestDto);
        return id;
    }

    @PutMapping("/api/v1/user-update/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserUpdateRequestDto userUpdateRequestDto, HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();

        userService.update(id, userUpdateRequestDto);

        httpSession.setAttribute("username", userUpdateRequestDto.getUsername());

        return id;
    }

    @PutMapping("/api/v1/user-following/{id}")
    public void following(@PathVariable Long id, @RequestBody UserFollowingRequestDto userFollowingRequestDto){
        userService.following(userFollowingRequestDto);
    }

    @PutMapping("/api/v1/user-unfollowing/{id}")
    public void unfollowing(@PathVariable Long id, @RequestBody UserFollowingRequestDto userFollowingRequestDto){
        userService.unfollowing(userFollowingRequestDto);
    }
}
