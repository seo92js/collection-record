package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.chatmessage.ChatMessage;

@Getter
@NoArgsConstructor
public class GetChatMessageResponseDto {
    private Long chatRoomId;
    private String senderName;
    private String receiverName;
    private String createdTime;
    private String message;

    public GetChatMessageResponseDto(ChatMessage chatMessage){
        this.chatRoomId = chatMessage.getChatRoom().getId();
        this.senderName = chatMessage.getSender().getUsername();
        this.receiverName = chatMessage.getReceiver().getUsername();
        this.createdTime = chatMessage.getCreatedDate();
        this.message = chatMessage.getMessage();
    }
}
