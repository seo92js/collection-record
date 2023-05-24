package side.collectionrecord.domain.category;

import side.collectionrecord.domain.user.User;

import java.util.List;

public interface CategoryRepositoryCustom {
    public List<Category> findAllCategory(User user);
}
