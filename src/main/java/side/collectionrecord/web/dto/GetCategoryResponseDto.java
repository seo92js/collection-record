package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.category.Category;

@Getter
@NoArgsConstructor
public class GetCategoryResponseDto {
    private Long id;
    private Long parentCategoryId;
    private String name;

    public GetCategoryResponseDto(Category category){
        this.id = category.getId();
        if (category.getParentCategory() != null)
            this.parentCategoryId = category.getParentCategory().getId();
        this.name = category.getName();
    }
}
