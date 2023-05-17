package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.service.UserService;
import side.collectionrecord.web.dto.UserJoinDto;
import side.collectionrecord.web.dto.UserLoginDto;

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
    public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto){
        User loginUser = userService.login(userLoginDto);

        if(loginUser == null){
            return ResponseEntity.badRequest().body("로그인에 실패하였습니다.");
        }else{
            return ResponseEntity.ok().body("로그인에 성공하였습니다.");
        }
    }
}
