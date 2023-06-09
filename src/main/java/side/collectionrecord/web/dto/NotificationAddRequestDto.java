package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotificationAddRequestDto {
    private String senderName;
    private String receiverName;
    private String text;

    private String url;

    @Builder
    public NotificationAddRequestDto(String senderName, String receiverName, String text, String url){
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.text = text;
        this.url = url;
    }
}
