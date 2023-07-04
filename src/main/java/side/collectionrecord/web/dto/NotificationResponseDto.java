package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.notification.Notification;

@Getter
@NoArgsConstructor
public class NotificationResponseDto {
    private Long id;
    private String senderName;
    private String receiverName;
    private String message;
    private String url;

    public NotificationResponseDto(Notification notification){
        this.id = notification.getId();
        this.senderName = notification.getSender().getUsername();
        this.receiverName = notification.getReceiver().getUsername();
        this.message = notification.getMessage();
        this.url = notification.getUrl();
    }
}
