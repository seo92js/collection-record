package side.collectionrecord.domain.chatmessage;

import java.util.List;

public interface ChatMessageRepositoryCustom {
    List<ChatMessage> findByChatRoomId(Long chatRoomId);

    List<ChatMessage> findByChatroomIdAndUserIdReadFalse(Long chatRoomId, Long userId);
}
