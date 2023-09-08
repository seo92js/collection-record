package side.collectionrecord.domain.userchatroom;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import java.util.List;

import static side.collectionrecord.domain.user.QUser.user;
import static side.collectionrecord.domain.userchatroom.QUserChatRoom.userChatRoom;

public class UserChatRoomRepositoryImpl implements UserChatRoomRepositoryCustom{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public UserChatRoomRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public UserChatRoom findByUserIds(Long user1Id, Long user2Id) {

/*        QUserChatRoom qUserChatRoom2 = new QUserChatRoom("userChatRoom2");

        UserChatRoom findUserChatRoom = queryFactory.select(userChatRoom)
                .from(userChatRoom, qUserChatRoom2)
                .where(userChatRoom.user.id.eq(user1Id)
                        .and(qUserChatRoom2.user.id.eq(user2Id))
                        .and(userChatRoom.chatRoom.eq(qUserChatRoom2.chatRoom))
                )
                .fetchFirst();
        }*/

        QUserChatRoom qUserChatRoom2 = new QUserChatRoom("userChatRoom2");

        UserChatRoom findUserChatRoom = queryFactory.select(userChatRoom)
                .from(userChatRoom)
                .join(qUserChatRoom2).on(userChatRoom.chatRoom.eq(qUserChatRoom2.chatRoom))
                .where(
                        userChatRoom.user.id.eq(user1Id)
                                .and(qUserChatRoom2.user.id.eq(user2Id))
                )
                .fetchFirst();

        return findUserChatRoom;
    }


    @Override
    public List<Tuple> findUserAndChatroomIdByUserId(Long userId) {
/*        List<Tuple> users = queryFactory.select(userChatRoom.user, userChatRoom.chatRoom.id)
                .from(userChatRoom)
                .where(userChatRoom.chatRoom.id.in(
                                JPAExpressions.select(userChatRoom.chatRoom.id)
                                        .from(userChatRoom)
                                        .where(userChatRoom.user.id.eq(userId)))
                        .and(userChatRoom.user.id.ne(userId)))
                .fetch();*/

        QUserChatRoom userChatRoom2 = new QUserChatRoom("user2");

        List<Tuple> users = queryFactory.select(userChatRoom.user, userChatRoom.chatRoom.id)
                .from(userChatRoom)
                .join(userChatRoom2)
                    .on(userChatRoom.chatRoom.id.eq(userChatRoom2.chatRoom.id)
                            .and(userChatRoom2.user.id.eq(userId))
                            .and(userChatRoom.user.id.ne(userId))
                    )
                .fetch();

        return users;
    }
}
