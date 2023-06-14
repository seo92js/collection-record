package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.service.ImageService;
import side.collectionrecord.service.UserService;
import side.collectionrecord.web.dto.ImageUploadRequestDto;
import side.collectionrecord.web.dto.UserJoinRequestDto;
import side.collectionrecord.web.dto.UserUpdateRequestDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    private final ImageService imageService;

    @PostMapping("/api/v1/user-join")
    public Long save (@RequestBody UserJoinRequestDto userJoinRequestDto){
        Long id = userService.join(userJoinRequestDto);
        return id;
    }

    @PutMapping("/api/v1/user-update/{id}")
    public Long update(@PathVariable Long id, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile, HttpServletRequest httpServletRequest) throws IOException {
        HttpSession httpSession = httpServletRequest.getSession();

        Image profileImage = null;

        if(imageFile != null && !imageFile.isEmpty()){
            byte[] image = imageFile.getBytes();

            Long imageId = imageService.upload(ImageUploadRequestDto.builder()
                                .data(image)
                                .build());

            profileImage = imageService.findImage(imageId);
        }

        UserUpdateRequestDto userUpdateRequestDto = UserUpdateRequestDto.builder()
                .username(username)
                .password(password)
                .profileImage(profileImage)
                .build();

        httpSession.setAttribute("username", userUpdateRequestDto.getUsername());

        userService.update(id, userUpdateRequestDto);

        return id;
    }
}
