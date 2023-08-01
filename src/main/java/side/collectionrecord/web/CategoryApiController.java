package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import side.collectionrecord.service.CategoryService;
import side.collectionrecord.web.dto.CategoryAddRequestDto;
import side.collectionrecord.web.dto.CategoryChildAddRequestDto;
import side.collectionrecord.web.dto.CategoryUpdateRequestDto;

@RequiredArgsConstructor
@RestController
public class CategoryApiController {
    private final CategoryService categoryService;

    @PostMapping("/api/v1/category-add")
    public Long add(@RequestBody CategoryAddRequestDto categoryAddRequestDto){
        Long id = categoryService.addCategory(categoryAddRequestDto);

        return id;
    }

    @PostMapping("/api/v1/category-child-add")
    public Long ChildAdd(@RequestBody CategoryChildAddRequestDto categoryChildAddRequestDto){
        Long id = categoryService.addCategoryChild(categoryChildAddRequestDto);

        return id;
    }

    @PutMapping("/api/v1/category-update/{id}")
    public Long update(@PathVariable Long id, @RequestBody CategoryUpdateRequestDto categoryUpdateRequestDto){
        categoryService.update(id, categoryUpdateRequestDto);

        return id;
    }

    @DeleteMapping("/api/v1/category-delete/{id}")
    public Long delete(@PathVariable Long id){
        categoryService.delete(id);

        return id;
    }
}
