package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UpdateCategoryRequestDto {
    private String name;

    @Builder
    public UpdateCategoryRequestDto(String name){
        this.name = name;
    }
}
