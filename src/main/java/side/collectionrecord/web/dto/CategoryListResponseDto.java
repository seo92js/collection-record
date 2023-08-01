package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.category.Category;

@Getter
@NoArgsConstructor
public class CategoryListResponseDto {
    private Long id;
    private Long parentCategoryId;
    private String name;

    public CategoryListResponseDto(Category category){
        this.id = category.getId();
        if (category.getParentCategory() != null)
            this.parentCategoryId = category.getParentCategory().getId();
        this.name = category.getName();
    }
}
