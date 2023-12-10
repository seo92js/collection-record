package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageRequestDto {

    private String filename;
    private byte[] data;

    @Builder
    public ImageRequestDto(String filename, byte[] data){
        this.filename = filename;
        this.data = data;
    }
}
