package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    private final UserService userService;

    private final CategoryService categoryService;

    private final FollowService followService;

    private final UserChatRoomService userChatRoomService;

    @GetMapping("/user/join")
    public String joinUserForm(Model model){
        model.addAttribute("userJoinRequestDto", new UserJoinRequestDto());
        //return "user/userJoinForm";

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken)
            return "user/userJoinForm";

        return "redirect:/";

    }

    @GetMapping("/user/{username}/home")
    public String userHome(@PathVariable String username, Model model){

        User user = userRepository.findByUsername(username).get();

        Long userId = user.getId();

        List<CategoryListResponseDto> categories = categoryService.findCategories(userId);

        model.addAttribute("categories", categories);

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

        return "user/userHome";
    }

    @GetMapping("/user/{username}/chatroomList")
    public String userChatroomList(@PathVariable String username, Model model){

        Long loginUserId = (Long) model.getAttribute("loginUserId");

        List<UserChatRoomListResponseDto> userChatRoomListResponseDtos = userChatRoomService.findUserChatRoomList(loginUserId);

        model.addAttribute("chatrooms", userChatRoomListResponseDtos);

        return "user/userChatroomList";
    }

    @GetMapping("/user/profile")
    public String userProfile(Model model){
        Long userId = (Long)model.getAttribute("loginUserId");

        UserProfileResponseDto userProfileResponseDto = userService.findById(userId);

        model.addAttribute("userProfileResponseDto", userProfileResponseDto);

        if (userProfileResponseDto.getProfileImage() != null){
            User user = userRepository.findById(userId).get();
            model.addAttribute("imageId", user.getProfileImage().getId());
        }

        return "user/userProfileForm";
    }
}
