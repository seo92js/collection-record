package side.collectionrecord.domain.category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryCustom {
    public Optional<Category> findByName(Long userId, String name);
    public List<Category> findAllParentCategory(Long userId);
    public List<Category> findAllChildCategory(Long userId);
}
