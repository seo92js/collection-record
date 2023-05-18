package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.service.UserService;
import side.collectionrecord.web.dto.UserJoinDto;
import side.collectionrecord.web.dto.UserLoginDto;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/api/v1/user-join")
    public Long save(@RequestBody UserJoinDto userJoinDto){
        Long id = userService.join(userJoinDto);
        return id;
    }

    @PostMapping("/api/v1/user-login")
    public ResponseEntity login(@RequestBody UserLoginDto userLoginDto, HttpServletRequest httpServletRequest){
        User loginUser = userService.login(userLoginDto);

        if(loginUser != null){
            HttpSession httpSession = httpServletRequest.getSession();
            httpSession.setAttribute("user", userLoginDto);
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }
}
