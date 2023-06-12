package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.service.CategoryService;
import side.collectionrecord.service.FollowService;
import side.collectionrecord.service.PostsService;
import side.collectionrecord.service.UserService;
import side.collectionrecord.web.dto.CategoryListResponseDto;
import side.collectionrecord.web.dto.UserFollowingRequestDto;
import side.collectionrecord.web.dto.UserJoinRequestDto;
import side.collectionrecord.web.dto.UserProfileResponseDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    private final UserService userService;

    private final CategoryService categoryService;

    private final FollowService followService;

    private final PostsService postsService;

    @GetMapping("/user/join")
    public String joinUserForm(Model model){
        model.addAttribute("userJoinRequestDto", new UserJoinRequestDto());
        return "user/userJoinForm";
    }

    @GetMapping("/user/{username}/home")
    public String userHome(@PathVariable String username, Model model, HttpServletRequest httpServletRequest){
        User user = userRepository.findByUsername(username).get();

        Long userId = user.getId();

        List<CategoryListResponseDto> categories = categoryService.findCategories(userId);

        model.addAttribute("categories", categories);

        HttpSession session = httpServletRequest.getSession(false);

        Long loginUserId = (Long)session.getAttribute("userId");

        if (loginUserId != userId) {
            if (followService.isFollowingUser(loginUserId, userId) == false) {
                model.addAttribute("isFollowing", false);
            } else {
                model.addAttribute("isFollowing", true);
            }
        }

        model.addAttribute("id", userId);
        model.addAttribute("username", username);

        return "user/userHome";
    }

    @GetMapping("/user/profile")
    public String userProfile(Model model, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(false);

        Long userId = (Long)session.getAttribute("userId");

        UserProfileResponseDto userProfileResponseDto = userService.findById(userId);

        model.addAttribute("userId", userId);
        model.addAttribute("userProfileResponseDto", userProfileResponseDto);

        return "user/userProfileForm";
    }
}
