package side.collectionrecord.domain.chatroom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import side.collectionrecord.domain.chatmessage.ChatMessage;
import side.collectionrecord.domain.chatmessage.QChatMessage;

import javax.persistence.EntityManager;

import java.util.List;

import static side.collectionrecord.domain.chatmessage.QChatMessage.chatMessage;
import static side.collectionrecord.domain.chatroom.QChatRoom.chatRoom;

public class ChatRoomRepositoryImpl implements ChatRoomRepositoryCustom{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ChatRoomRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public boolean readAllMessage(Long chatRoomId, Long receiverId) {
        List<ChatRoom> fetch = queryFactory.selectFrom(chatRoom)
                .join(chatRoom.chatMessages)
                .where(chatRoom.chatMessages.any().receiver.id.eq(receiverId).and(chatRoom.chatMessages.any().read.eq(false)))
                .fetch();

        if (fetch.size() > 0)
            return false;
        else
            return true;
    }
}
