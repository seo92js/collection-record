package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ImageUploadRequestDto {

    private String filename;
    private byte[] data;

    @Builder
    public ImageUploadRequestDto(String filename, byte[] data){
        this.filename = filename;
        this.data = data;
    }
}
