package side.collectionrecord.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.config.auth.dto.LoginRequestDto;
import side.collectionrecord.domain.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class LoginApiController {
    private final LoginService loginService;

    @PostMapping("/api/v1/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest httpServletRequest){

        User loginUser = loginService.login(loginRequestDto);

        if(loginUser != null){
            HttpSession httpSession = httpServletRequest.getSession();
            httpSession.setAttribute("loginUserId", loginUser.getId());
            httpSession.setAttribute("loginUsername", loginUser.getUsername());

            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/api/v1/logout")
    public void logout(Model model){
        HttpSession session = (HttpSession) model.getAttribute("session");

        session.invalidate();
    }
}
