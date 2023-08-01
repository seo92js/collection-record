package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryChildAddRequestDto {
    private Long userId;
    private Long parentCategoryId;
    private String name;

    @Builder
    public CategoryChildAddRequestDto(Long userId, Long parentCategoryId, String name) {
        this.userId = userId;
        this.parentCategoryId = parentCategoryId;
        this.name = name;
    }
}
