package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMessageAddRequestDto {
    private Long senderId;
    private Long receiverId;

    private Long chatRoomId;
    private String message;

    @Builder
    public ChatMessageAddRequestDto(Long senderId, Long receiverId, Long chatRoomId, String message){
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.chatRoomId = chatRoomId;
        this.message = message;
    }
}
