package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import side.collectionrecord.service.CategoryService;
import side.collectionrecord.web.dto.CreateChildCategoryRequestDto;
import side.collectionrecord.web.dto.CreateParentCategoryRequestDto;
import side.collectionrecord.web.dto.UpdateCategoryRequestDto;

@RequiredArgsConstructor
@RestController
public class CategoryApiController {
    private final CategoryService categoryService;

    @PostMapping("/api/v1/parent-category")
    public Long createParentCategory(@RequestBody CreateParentCategoryRequestDto createParentCategoryRequestDto){
        Long id = categoryService.createParentCategory(createParentCategoryRequestDto);

        return id;
    }

    @PostMapping("/api/v1/child-category")
    public Long createChildCategory(@RequestBody CreateChildCategoryRequestDto createChildCategoryRequestDto){
        Long id = categoryService.createChildCategory(createChildCategoryRequestDto);

        return id;
    }

    @PutMapping("/api/v1/category/{id}")
    public Long updateCategory(@PathVariable Long id, @RequestBody UpdateCategoryRequestDto updateCategoryRequestDto){
        categoryService.updateCategory(id, updateCategoryRequestDto);

        return id;
    }

    @DeleteMapping("/api/v1/category/{id}")
    public Long deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);

        return id;
    }
}
