package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.notification.Notification;

@Getter
@NoArgsConstructor
public class GetNotificationResponseDto {
    private String createdDate;
    private Long id;
    private String senderName;
    private String receiverName;
    private String message;
    private String url;

    public GetNotificationResponseDto(Notification notification){
        this.createdDate = notification.getCreatedDate();
        this.id = notification.getId();
        this.senderName = notification.getSender().getUsername();
        this.receiverName = notification.getReceiver().getUsername();
        this.message = notification.getMessage();
        this.url = notification.getUrl();
    }
}
