package side.collectionrecord.domain.category;

import java.util.List;

public interface CategoryRepositoryCustom {
    public List<Category> findAllCategory(Long userId);
}
