package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.service.UserService;
import side.collectionrecord.web.dto.UserJoinRequestDto;
import side.collectionrecord.web.dto.UserLoginRequestDto;
import side.collectionrecord.web.dto.UserProfileResponseDto;
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


    @PostMapping("/api/v1/user-login")
    public ResponseEntity login(@RequestBody UserLoginRequestDto userLoginRequestDto, HttpServletRequest httpServletRequest){
        User loginUser = userService.login(userLoginRequestDto);

        if(loginUser != null){
            HttpSession httpSession = httpServletRequest.getSession();
            httpSession.setAttribute("userId", loginUser.getId());
            httpSession.setAttribute("username", loginUser.getUsername());
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("api/v1/user-logout")
    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        session.invalidate();
    }

    @PutMapping("api/v1/user-update/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserUpdateRequestDto userUpdateRequestDto, HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();

        //Long userId = (Long) httpSession.getAttribute("userId");

        userService.update(id, userUpdateRequestDto);

        httpSession.setAttribute("username", userUpdateRequestDto.getUsername());

        return id;
    }
}
