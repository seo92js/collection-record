package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateChatMessageRequestDto {
    private Long senderId;
    private Long receiverId;

    private Long chatRoomId;
    private String message;
    private boolean read;

    @Builder
    public CreateChatMessageRequestDto(Long senderId, Long receiverId, Long chatRoomId, String message, Boolean read){
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.chatRoomId = chatRoomId;
        this.message = message;
        this.read = read;
    }
}
