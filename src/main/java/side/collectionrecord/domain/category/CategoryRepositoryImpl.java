package side.collectionrecord.domain.category;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static side.collectionrecord.domain.category.QCategory.category;

public class CategoryRepositoryImpl implements CategoryRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public CategoryRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Category findByName(Long userId, String name){
        return queryFactory.selectFrom(category)
                .where(category.user.id.eq(userId).and(category.name.eq(name)))
                .fetchOne();
    }

    @Override
    public List<Category> findAllCategory(Long userId){
        return queryFactory.selectFrom(category)
                .where(category.user.id.eq(userId))
                .fetch();
    }
}
