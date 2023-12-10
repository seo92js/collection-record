package side.collectionrecord.domain.posts;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

import static side.collectionrecord.domain.posts.QPosts.posts;

public class PostsRepositoryImpl implements PostsRepositoryCustom{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public PostsRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Posts> findByArtistContains(String artist, Pageable pageable){
        return queryFactory.selectFrom(posts)
                .where(posts.artist.containsIgnoreCase(artist))
                .orderBy(posts.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<Posts> findByUserIdAndArtist(Long userId, String artist, Pageable pageable){
        if (Objects.equals(artist, "all")){
            return queryFactory.selectFrom(posts)
                    .where(posts.user.id.eq(userId))
                    .orderBy(posts.createdDate.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
        }else{
            return queryFactory.selectFrom(posts)
                    .where(posts.user.id.eq(userId).and(posts.artist.eq(artist)))
                    .orderBy(posts.createdDate.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
        }
    }

    @Override
    public List<Posts> findByUserIdAndCategory(Long userId, String category, Pageable pageable){
        if (Objects.equals(category, "all")){
            return queryFactory.selectFrom(posts)
                    .where(posts.user.id.eq(userId))
                    .orderBy(posts.createdDate.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
        }else{
            return queryFactory.selectFrom(posts)
                    .where(posts.user.id.eq(userId).and(posts.category.stringValue().eq(category)))
                    .orderBy(posts.createdDate.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
        }
    }

    @Override
    public List<Posts> findByAlbumContains(String album, Pageable pageable){
        return queryFactory.selectFrom(posts)
                .where(posts.album.containsIgnoreCase(album))
                .orderBy(posts.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<String> findArtistByUserId(Long userId){
        return queryFactory.selectDistinct(posts.artist)
                .from(posts)
                .where(posts.user.id.eq(userId))
                .orderBy(posts.artist.asc())
                .fetch();
    }
}
