package side.collectionrecord.domain.image;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Image {
    @Id @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    @Lob
    private byte[] data;

    @Builder
    public Image(byte[] data){
        this.data = data;
    }
}
