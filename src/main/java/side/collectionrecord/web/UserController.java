package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.service.UserService;
import side.collectionrecord.web.dto.UserJoinRequestDto;
import side.collectionrecord.web.dto.UserLoginRequestDto;
import side.collectionrecord.web.dto.UserProfileResponseDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/join")
    public String joinUserForm(Model model){
        model.addAttribute("userJoinRequestDto", new UserJoinRequestDto());
        return "user/userJoinForm";
    }

    @GetMapping("/user/login")
    public String loginUserForm(Model model){
        model.addAttribute("userLoginRequestDto", new UserLoginRequestDto());
        return "user/userLoginForm";
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
