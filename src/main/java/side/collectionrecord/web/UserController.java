package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.UserJoinDto;
import side.collectionrecord.web.dto.UserLoginDto;
import side.collectionrecord.web.dto.UserProfileDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/user/join")
    public String joinUserForm(Model model){
        model.addAttribute("userJoinDto", new UserJoinDto());
        return "user/userJoinForm";
    }

    @GetMapping("/user/login")
    public String loginUserForm(Model model){
        model.addAttribute("userLoginDto", new UserLoginDto());
        return "user/userLoginForm";
    }

    @GetMapping("/user/profile")
    public String userProfile(Model model, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(false);

        Long userId = (Long)session.getAttribute("userId");

        User findUser = userRepository.findById(userId).orElse(null);

        UserProfileDto userProfileDto = new UserProfileDto().builder()
                .username(findUser.getUsername())
                .password(findUser.getPassword())
                .image(findUser.getImage())
                .build();

        model.addAttribute("userProfileDto", userProfileDto);

        return "user/userProfileForm";
    }
}
