package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateParentCategoryRequestDto {

    private Long userId;
    private String name;

    @Builder
    public CreateParentCategoryRequestDto(Long userId, String name){
        this.userId = userId;
        this.name = name;
    }
}
