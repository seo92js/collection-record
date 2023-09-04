package side.collectionrecord.domain.notification;

import java.util.List;

public interface NotificationRepositoryCustom {
    List<Notification> findByUserIdConfirmFalse(Long userId);
}
