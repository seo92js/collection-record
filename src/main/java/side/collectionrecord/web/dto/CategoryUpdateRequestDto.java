package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CategoryUpdateRequestDto {
    private String name;

    @Builder
    public CategoryUpdateRequestDto(String name){
        this.name = name;
    }
}
