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

    @Builder
    public NotificationAddRequestDto(String senderName, String receiverName, String text){
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.text = text;
    }
}
