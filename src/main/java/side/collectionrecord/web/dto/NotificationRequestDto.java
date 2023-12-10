package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.BaseTimeEntity;

@Getter
@NoArgsConstructor
public class NotificationRequestDto extends BaseTimeEntity {
    private String senderName;
    private String receiverName;
    private String text;

    private String url;

    @Builder
    public NotificationRequestDto(String senderName, String receiverName, String text, String url){
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.text = text;
        this.url = url;
    }
}
