package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageUploadRequestDto {
    private byte[] data;

    @Builder
    public ImageUploadRequestDto(byte[] data){
        this.data = data;
    }
}
