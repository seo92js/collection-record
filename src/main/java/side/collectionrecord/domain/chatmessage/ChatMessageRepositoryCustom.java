package side.collectionrecord.domain.chatmessage;

import java.util.List;

public interface ChatMessageRepositoryCustom {
    List<ChatMessage> findByChatRoomId(Long chatRoomId);

    List<ChatMessage> findByChatroomIdAndUserIdConfirmFalse(Long chatRoomId, Long userId);
}
