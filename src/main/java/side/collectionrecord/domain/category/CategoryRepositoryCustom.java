package side.collectionrecord.domain.category;

import java.util.List;

public interface CategoryRepositoryCustom {
    public Category findByName(Long userId, String name);
    public List<Category> findAllCategory(Long userId);
}
