package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.chatmessage.ChatMessage;

@Getter
@NoArgsConstructor
public class ChatMessageResponseDto {
    private String senderName;
    private String receiverName;
    private String createdTime;
    private String message;

    public ChatMessageResponseDto(ChatMessage chatMessage){
        this.senderName = chatMessage.getSender().getUsername();
        this.receiverName = chatMessage.getReceiver().getUsername();
        this.createdTime = chatMessage.getCreatedDate();
        this.message = chatMessage.getMessage();
    }
}
