package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.service.FollowService;
import side.collectionrecord.service.UserChatRoomService;
import side.collectionrecord.service.UserService;
import side.collectionrecord.web.dto.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    private final UserService userService;

    private final FollowService followService;

    private final UserChatRoomService userChatRoomService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/user/join")
    public String joinUserForm(Model model) {
        model.addAttribute("createUserRequestDto", new CreateUserRequestDto());

        return "user/userJoinForm";
    }

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
    }

    @GetMapping("/user/{username}/home")
    public String userHome(@PathVariable String username, Model model){

        User user = userRepository.findByUsername(username).get();

        Long userId = user.getId();

        List<Category> categories = Arrays.asList(Category.values());
        model.addAttribute("categories", categories);

        Long loginUserId = (Long) model.getAttribute("loginUserId");

        if (loginUserId != userId) {
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
        model.addAttribute("username", username);
        model.addAttribute("profileText", user.getProfileText());

        return "user/userHome";
    }

    @GetMapping("/user/{username}/chatroomList")
    public String userChatroomList(@PathVariable String username, Model model){

        Long loginUserId = (Long) model.getAttribute("loginUserId");

        List<GetUserChatRoomResponseDto> getUserChatRoomResponseDtos = userChatRoomService.getAllUserChatroomByUserId(loginUserId);

        model.addAttribute("chatrooms", getUserChatRoomResponseDtos);

        return "user/userChatroomList";
    }

    @GetMapping("/user/profile")
    public String userProfile(Model model){
        Long userId = (Long)model.getAttribute("loginUserId");

        GetUserProfileResponseDto getUserProfileResponseDto = userService.getUserById(userId);

        model.addAttribute("getUserProfileResponseDto", getUserProfileResponseDto);

        if (getUserProfileResponseDto.getProfileImage() != null){
            User user = userRepository.findById(userId).get();
            model.addAttribute("imageId", user.getProfileImage().getId());
        }

        return "user/userProfileForm";
    }

    @GetMapping("user/profile/password")
    public String userPassword(Model model){
        model.addAttribute("userPasswordUpdateRequestDto", new UpdateUserPasswordRequestDto());

        return "user/userPasswordForm";
    }
}
