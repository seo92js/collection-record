package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import side.collectionrecord.service.CategoryService;
import side.collectionrecord.web.dto.CreateParentCategoryRequestDto;
import side.collectionrecord.web.dto.GetCategoryResponseDto;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public String getAllCategory(Model model){
        Long userId = (Long)model.getAttribute("loginUserId");


        model.addAttribute("createParentCategoryRequestDto", CreateParentCategoryRequestDto.builder()
                        .userId(userId)
                        .name(null)
                        .build());

        List<GetCategoryResponseDto> parentCategories = categoryService.getAllParentCategoryByUserId(userId);
        model.addAttribute("parentCategories", parentCategories);

        List<GetCategoryResponseDto> childCategories = categoryService.getAllChildCategoryByUserId(userId);
        model.addAttribute("childCategories", childCategories);

        return "category/category";
    }
}
