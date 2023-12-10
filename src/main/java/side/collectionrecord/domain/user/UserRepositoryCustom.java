package side.collectionrecord.domain.user;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findByUsernameContains(String username, Pageable pageable);
}
