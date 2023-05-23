package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.service.CategoryService;
import side.collectionrecord.web.dto.CategoryAddRequestDto;

@RequiredArgsConstructor
@RestController
public class CategoryApiController {
    private final CategoryService categoryService;

    @PostMapping("/api/v1/category-add")
    public Long add(@RequestBody CategoryAddRequestDto categoryAddRequestDto){
        Long id = categoryService.addCategory(categoryAddRequestDto);

        return id;
    }
}
