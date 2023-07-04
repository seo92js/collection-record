package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.notification.Notification;

@Getter
@NoArgsConstructor
public class NotificationResponseDto {
    private String senderName;
    private String receiverName;
    private String message;

    public NotificationResponseDto(Notification notification){
        this.senderName = notification.getSender().getUsername();
        this.receiverName = notification.getReceiver().getUsername();
        this.message = notification.getMessage();
    }
}
