package side.collectionrecord.domain.user;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static side.collectionrecord.domain.user.QUser.user;

public class UserRepositoryImpl implements UserRepositoryCustom{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<User> findByUsernameContains(String username, int offset, int size) {
        return queryFactory.selectFrom(user)
                .where(user.username.contains(username))
                .orderBy(user.username.asc())
                .offset(offset)
                .limit(size)
                .fetch();
    }
}
