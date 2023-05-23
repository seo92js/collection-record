package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.h2.engine.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.service.CategoryService;
import side.collectionrecord.web.dto.CategoryAddRequestDto;
import side.collectionrecord.web.dto.CategoryListResponseDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class CategoryController {

    private final UserRepository userRepository;
    private final CategoryService categoryService;

    @GetMapping("/category")
    public String categories(Model model, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(false);

        Long userId = (Long)session.getAttribute("userId");

        User user = userRepository.findById(userId).get();

        List<CategoryListResponseDto> categories = categoryService.findCategories(user);

        model.addAttribute("categoryAddRequestDto", new CategoryAddRequestDto());
        model.addAttribute("categories", categories);

        return "category/categorySetting";
    }
}
