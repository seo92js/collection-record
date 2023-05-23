package side.collectionrecord.domain.category;

import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.stereotype.Repository;
import side.collectionrecord.domain.user.User;

import javax.persistence.EntityManager;
import java.util.List;

import static side.collectionrecord.domain.category.QCategory.category;

@Repository
public class CategoryRepository{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public CategoryRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public void save(Category category){
        em.persist(category);
    }

    public List<Category> findAllCategory(User user){
        return queryFactory.selectFrom(category)
                .where(category.user.eq(user))
                .fetch();
    }
}
