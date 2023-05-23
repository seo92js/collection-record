package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.category.Category;

@Getter
@NoArgsConstructor
public class CategoryListResponseDto {
    private String name;

    public CategoryListResponseDto(Category category){
        this.name = category.getName();
    }
}
