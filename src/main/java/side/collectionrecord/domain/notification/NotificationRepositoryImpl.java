package side.collectionrecord.domain.notification;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static side.collectionrecord.domain.notification.QNotification.notification;

public class NotificationRepositoryImpl implements NotificationRepositoryCustom{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public NotificationRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Notification> findByUserIdReadFalse(Long userId) {
        return queryFactory.selectFrom(notification)
                .where(notification.receiver.id.eq(userId).and(notification.read.eq(false)))
                .orderBy(notification.createdDate.desc())
                .fetch();
    }
}
