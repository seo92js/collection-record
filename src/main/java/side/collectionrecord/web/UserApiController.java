package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.service.ImageService;
import side.collectionrecord.service.UserService;
import side.collectionrecord.web.dto.ImageUploadRequestDto;
import side.collectionrecord.web.dto.UserJoinRequestDto;
import side.collectionrecord.web.dto.UserUpdateRequestDto;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserRepository userRepository;

    private final UserService userService;

    private final ImageService imageService;

/*    @PostMapping("/api/v1/user-join")
    public Long save (@RequestBody UserJoinRequestDto userJoinRequestDto) throws IOException {
        Long id = userService.join(userJoinRequestDto);
        return id;
    }*/

    @PutMapping("/api/v1/user-update/{id}")
    public Long update(Model model, @PathVariable Long id, @RequestPart(value = "userUpdateRequestDto") UserUpdateRequestDto userUpdateRequestDto, @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        Long imageId;

        HttpSession httpSession = (HttpSession) model.getAttribute("session");

        Image profileImage = null;

        if(imageFile != null){
            byte[] image = imageFile.getBytes();

            imageId = imageService.upload(ImageUploadRequestDto.builder()
                    .filename(imageFile.getOriginalFilename())
                    .data(image)
                    .build());

            profileImage = imageService.findImage(imageId);
        }else{
            User user = userRepository.findById(id).get();

            profileImage = imageService.findImage(user.getProfileImage().getId());
        }

        userUpdateRequestDto.setProfileImage(profileImage);

        httpSession.setAttribute("loginUsername", userUpdateRequestDto.getUsername());

        userService.update(id, userUpdateRequestDto);

        return id;
    }
}
