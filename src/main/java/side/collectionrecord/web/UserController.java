package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.service.FollowService;
import side.collectionrecord.service.PostsService;
import side.collectionrecord.service.UserChatRoomService;
import side.collectionrecord.service.UserService;
import side.collectionrecord.web.dto.GetUserChatRoomResponseDto;
import side.collectionrecord.web.dto.GetUserProfileResponseDto;

import java.util.Arrays;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    private final UserService userService;

    private final FollowService followService;

    private final PostsService postsService;

    private final UserChatRoomService userChatRoomService;

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

        List<String> artists = postsService.getAllArtistByUserId(userId);
        model.addAttribute("artists", artists);

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
}
