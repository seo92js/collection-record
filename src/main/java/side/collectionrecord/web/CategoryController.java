package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import side.collectionrecord.service.CategoryService;
import side.collectionrecord.web.dto.CategoryAddRequestDto;
import side.collectionrecord.web.dto.CategoryListResponseDto;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public String categories(Model model){
        Long userId = (Long)model.getAttribute("loginUserId");


        model.addAttribute("categoryAddRequestDto", CategoryAddRequestDto.builder()
                        .userId(userId)
                        .name(null)
                        .build());

        List<CategoryListResponseDto> parentCategories = categoryService.findParentCategories(userId);
        model.addAttribute("parentCategories", parentCategories);

        List<CategoryListResponseDto> childCategories = categoryService.findChildCategories(userId);
        model.addAttribute("childCategories", childCategories);

        return "category/category";
    }
}
