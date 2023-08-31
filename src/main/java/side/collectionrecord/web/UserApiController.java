package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.service.ImageService;
import side.collectionrecord.service.UserService;
import side.collectionrecord.web.dto.CreateImageRequestDto;
import side.collectionrecord.web.dto.UpdateUserRequestDto;

import javax.validation.Valid;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserRepository userRepository;

    private final UserService userService;

    private final ImageService imageService;

/*
 나중에 참고

 api라 안해도 될 거 같긴한데

<div th:if="${error}">
        <script th:inline="javascript">
            alert([[${error}]]);
        </script>
    </div>

@PostMapping("/user/join")
    public String joinUserForm(@Valid @ModelAttribute CreateUserRequestDto createUserRequestDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("error", bindingResult.getFieldError().getDefaultMessage());

            return "redirect:/user/join";
        }else {
            createUserRequestDto.encodePassword(passwordEncoder);

            userService.createUser(createUserRequestDto);

            return "redirect:/";
        }
    }*/

    @PutMapping("/api/v1/user/{id}")
    public Long updateUser(Model model, @PathVariable Long id, @Valid @RequestPart(value = "updateUserRequestDto") UpdateUserRequestDto updateUserRequestDto, @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        Long imageId;

        Image profileImage = null;

        if(imageFile != null){
            byte[] image = imageFile.getBytes();

            imageId = imageService.createImage(CreateImageRequestDto.builder()
                    .filename(imageFile.getOriginalFilename())
                    .data(image)
                    .build());

            profileImage = imageService.getImageById(imageId);
        }else{
            User user = userRepository.findById(id).get();

            profileImage = imageService.getImageById(user.getProfileImage().getId());
        }

        updateUserRequestDto.setProfileImage(profileImage);

        userService.updateUser(id, updateUserRequestDto);

        return id;
    }
}
