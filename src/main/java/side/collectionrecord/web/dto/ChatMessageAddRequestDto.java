package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMessageAddRequestDto {
    private Long userId;
    private Long chatRoomId;
    private String message;

    @Builder
    public ChatMessageAddRequestDto(Long userId, Long chatRoomId, String message){
        this.userId = userId;
        this.chatRoomId = chatRoomId;
        this.message = message;
    }
}
