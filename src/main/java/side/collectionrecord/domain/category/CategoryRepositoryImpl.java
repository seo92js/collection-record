package side.collectionrecord.domain.category;

import com.querydsl.jpa.impl.JPAQueryFactory;
import side.collectionrecord.domain.user.User;

import javax.persistence.EntityManager;
import java.util.List;

import static side.collectionrecord.domain.category.QCategory.category;

public class CategoryRepositoryImpl implements CategoryRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public CategoryRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Category> findAllCategory(User user){
        return queryFactory.selectFrom(category)
                .where(category.user.eq(user))
                .fetch();
    }
}
