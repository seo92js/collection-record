package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.service.CategoryService;
import side.collectionrecord.service.FollowService;
import side.collectionrecord.service.UserChatRoomService;
import side.collectionrecord.service.UserService;
import side.collectionrecord.web.dto.CategoryListResponseDto;
import side.collectionrecord.web.dto.UserChatRoomListResponseDto;
import side.collectionrecord.web.dto.UserJoinRequestDto;
import side.collectionrecord.web.dto.UserProfileResponseDto;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    private final UserService userService;

    private final CategoryService categoryService;

    private final FollowService followService;

    private final UserChatRoomService userChatRoomService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/user/join")
    public String joinUserForm(Model model) {
        model.addAttribute("userJoinRequestDto", new UserJoinRequestDto());

        return "user/userJoinForm";
    }

    @PostMapping("/user/join")
    public String joinUserForm(@Valid @ModelAttribute UserJoinRequestDto userJoinRequestDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("error", bindingResult.getFieldError().getDefaultMessage());

            return "redirect:/user/join";
        }else {
            userJoinRequestDto.encodePassword(passwordEncoder);

            userService.createUser(userJoinRequestDto);

            return "redirect:/";
        }
    }

    @GetMapping("/user/{username}/home")
    public String userHome(@PathVariable String username, Model model){

        User user = userRepository.findByUsername(username).get();

        Long userId = user.getId();

        List<CategoryListResponseDto> parentCategories = categoryService.findParentCategories(userId);
        model.addAttribute("parentCategories", parentCategories);

        List<CategoryListResponseDto> childCategories = categoryService.findChildCategories(userId);
        model.addAttribute("childCategories", childCategories);

        Long loginUserId = (Long) model.getAttribute("loginUserId");

        if (loginUserId != userId) {
            if (followService.isFollowingUser(loginUserId, userId) == false) {
                model.addAttribute("isFollowing", false);
            } else {
                model.addAttribute("isFollowing", true);
            }
        }

        if (user.getProfileImage() != null){
            model.addAttribute("imageId", user.getProfileImage().getId());
        }

        model.addAttribute("id", userId);
        model.addAttribute("username", username);
        model.addAttribute("profileText", user.getProfileText());

        return "user/userHome";
    }

    @GetMapping("/user/{username}/chatroomList")
    public String userChatroomList(@PathVariable String username, Model model){

        Long loginUserId = (Long) model.getAttribute("loginUserId");

        List<UserChatRoomListResponseDto> userChatRoomListResponseDtos = userChatRoomService.getAllUserChatroomByUserId(loginUserId);

        model.addAttribute("chatrooms", userChatRoomListResponseDtos);

        return "user/userChatroomList";
    }

    @GetMapping("/user/profile")
    public String userProfile(Model model){
        Long userId = (Long)model.getAttribute("loginUserId");

        UserProfileResponseDto userProfileResponseDto = userService.getUserById(userId);

        model.addAttribute("userProfileResponseDto", userProfileResponseDto);

        if (userProfileResponseDto.getProfileImage() != null){
            User user = userRepository.findById(userId).get();
            model.addAttribute("imageId", user.getProfileImage().getId());
        }

        return "user/userProfileForm";
    }
}
