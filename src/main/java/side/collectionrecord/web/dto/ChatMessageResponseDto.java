package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.chatmessage.ChatMessage;

@Getter
@NoArgsConstructor
public class ChatMessageResponseDto {
    private String username;
    private String createdTime;
    private String message;

    public ChatMessageResponseDto(ChatMessage chatMessage){
        this.username = chatMessage.getUser().getUsername();
        this.createdTime = chatMessage.getCreatedDate();
        this.message = chatMessage.getMessage();
    }
}
