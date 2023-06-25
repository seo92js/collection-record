package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMessageAddDto {
    private Long userId;
    private Long chatRoomId;
    private String text;

    @Builder
    public ChatMessageAddDto(Long userId, Long chatRoomId, String text){
        this.userId = userId;
        this.chatRoomId = chatRoomId;
        this.text = text;
    }
}
