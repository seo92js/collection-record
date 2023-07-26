package side.collectionrecord.domain.chatroom;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class ChatRoomRepositoryImpl implements ChatRoomRepositoryCustom{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ChatRoomRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }
}
