package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserRepository userRepository;

    private final UserService userService;

    private final ImageService imageService;

    private final PasswordEncoder passwordEncoder;

    @PutMapping("/api/v1/user/{id}")
    public Long updateUser(Model model, @PathVariable Long id, @RequestPart(value = "updateUserRequestDto") UpdateUserRequestDto updateUserRequestDto, @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
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
        //userUpdateRequestDto.encodePassword(passwordEncoder);

        userService.updateUser(id, updateUserRequestDto);

        // 변경된 사용자 정보를 다시 로드하여 Spring Security에 반영
        UserDetails updatedUserDetails = userService.loadUserByUsername(updateUserRequestDto.getUsername());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                updatedUserDetails, null, updatedUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return id;
    }
}
