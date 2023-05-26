package side.collectionrecord.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.service.CategoryService;
import side.collectionrecord.web.dto.CategoryListResponseDto;
import side.collectionrecord.web.dto.PostsAddRequestDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PostsController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/posts")
    public String posts(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();

        Long userId = (Long)session.getAttribute("userId");

        User user = userRepository.findById(userId).get();

        List<CategoryListResponseDto> categories = categoryService.findCategories(user);

        model.addAttribute("postsAddRequestDto", PostsAddRequestDto.builder()
                        .userId(userId)
                        .categoryName(null)
                        .title(null)
                        .image(null)
                        .text(null)
                        .build());

        model.addAttribute("categories", categories);

        return "posts/posts";
    }
}
