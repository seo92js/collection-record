package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateImageRequestDto {

    private String filename;
    private byte[] data;

    @Builder
    public CreateImageRequestDto(String filename, byte[] data){
        this.filename = filename;
        this.data = data;
    }
}
