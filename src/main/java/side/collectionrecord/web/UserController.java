package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.service.CategoryService;
import side.collectionrecord.service.UserService;
import side.collectionrecord.web.dto.CategoryListResponseDto;
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

    @GetMapping("/user/join")
    public String joinUserForm(Model model){
        model.addAttribute("userJoinRequestDto", new UserJoinRequestDto());
        return "user/userJoinForm";
    }

    @GetMapping("/user/home")
    public String userHome(Model model, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(false);

        Long userId = (Long)session.getAttribute("userId");

        User user = userRepository.findById(userId).get();

        List<CategoryListResponseDto> categories = categoryService.findCategories(user);

        model.addAttribute("categories", categories);
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
