package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import side.collectionrecord.service.UserService;
import side.collectionrecord.web.dto.UserJoinDto;
import side.collectionrecord.web.dto.UserUpdateDto;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/api/v1/users")
    public Long save(@RequestBody UserJoinDto userJoinDto){
        Long id = userService.join(userJoinDto);
        return id;
    }

    @PutMapping("api/v1/users/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserUpdateDto updateDto){
        return userService.update(id, updateDto);
    }
}
