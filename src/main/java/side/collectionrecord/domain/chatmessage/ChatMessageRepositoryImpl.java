package side.collectionrecord.domain.chatmessage;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static side.collectionrecord.domain.chatmessage.QChatMessage.chatMessage;

public class ChatMessageRepositoryImpl implements ChatMessageRepositoryCustom{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ChatMessageRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ChatMessage> findByChatRoomId(Long chatRoomId) {

        return queryFactory.selectFrom(chatMessage)
                .where(chatMessage.chatRoom.id.eq(chatRoomId))
                .orderBy(chatMessage.createdDate.asc())
                .fetch();
    }

    @Override
    public List<ChatMessage> findByChatroomIdAndUserIdConfirmFalse(Long chatRoomId, Long userId){

        return queryFactory.selectFrom(chatMessage)
                .where(chatMessage.chatRoom.id.eq(chatRoomId).and(chatMessage.receiver.id.eq(userId).and(chatMessage.confirm.eq(false))))
                .fetch();
    }
}
