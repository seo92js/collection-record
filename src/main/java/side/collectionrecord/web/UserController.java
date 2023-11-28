package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.engine.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import side.collectionrecord.config.auth.dto.SessionUser;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.service.*;
import side.collectionrecord.web.dto.CreateImageRequestDto;
import side.collectionrecord.web.dto.GetUserChatRoomResponseDto;
import side.collectionrecord.web.dto.UserProfileForm;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    private final UserService userService;

    private final FollowService followService;

    private final PostsService postsService;

    private final UserChatRoomService userChatRoomService;

    private final ImageService imageService;

    @GetMapping("/user/{id}/home")
    public String userHome(@PathVariable Long id, Model model, HttpSession httpSession){
        User user = userRepository.findById(id).get();

        Long userId = user.getId();

        List<Category> categories = Arrays.asList(Category.values());
        model.addAttribute("categories", categories);

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        Long loginUserId = sessionUser.getId();

        if (!loginUserId.equals(userId)) {
            if (followService.checkFollow(loginUserId, userId) == false) {
                model.addAttribute("isFollowing", false);
            } else {
                model.addAttribute("isFollowing", true);
            }
        }

        if (user.getProfileImage() != null){
            model.addAttribute("imageId", user.getProfileImage().getId());
        }

        model.addAttribute("id", userId);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("profileText", user.getProfileText());

        List<String> artists = postsService.getAllArtistByUserId(userId);
        model.addAttribute("artists", artists);

        return "user/userHome";
    }

    @GetMapping("/user/{username}/chatroomList")
    public String userChatroomList(@PathVariable String username, Model model, HttpSession httpSession){

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        List<GetUserChatRoomResponseDto> getUserChatRoomResponseDtos = userChatRoomService.getAllUserChatroomByUserId(user.getId());

        model.addAttribute("chatrooms", getUserChatRoomResponseDtos);

        return "user/userChatroomList";
    }

    @GetMapping("/user/{id}/profile")
    public String userProfile(@PathVariable Long id, Model model){
        UserProfileForm userProfileForm = userService.findById(id);

        User user = userRepository.findById(id).get();

        model.addAttribute("imageId", user.getProfileImage().getId());

        model.addAttribute("userProfileForm", userProfileForm);

        return "user/userProfileForm";
    }

    @PostMapping("/user/{id}/profile")
    public String userProfileUpdate(@PathVariable Long id, @Validated @ModelAttribute UserProfileForm userProfileForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            User user = userRepository.findById(id).get();
            model.addAttribute("imageId", user.getProfileImage().getId());
            return "user/userProfileForm";
        }

        Image profileImage = null;

        if (!userProfileForm.getProfileImage().getOriginalFilename().equals("")) {
            byte[] bytes = userProfileForm.getProfileImage().getBytes();

            Long imageId = imageService.createImage(CreateImageRequestDto.builder()
                    .filename(userProfileForm.getProfileImage().getOriginalFilename())
                    .data(bytes)
                    .build());

            profileImage = imageService.getImageById(imageId);

        } else {
            User user = userRepository.findById(id).get();

            profileImage = imageService.getImageById(user.getProfileImage().getId());
        }

        userService.userUpdate(id, userProfileForm, profileImage);

        redirectAttributes.addAttribute("userId", id);
        return "redirect:/user/{userId}/home";
    }
}
