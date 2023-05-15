package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.service.UserService;

import java.lang.reflect.Member;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/api/v1/users")
    public Long save(@RequestBody User user){
        Long id = userService.join(user);
        return id;
    }

}
