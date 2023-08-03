package side.collectionrecord.domain.user;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findByUsernameContains(String username, int offset, int size);
}
