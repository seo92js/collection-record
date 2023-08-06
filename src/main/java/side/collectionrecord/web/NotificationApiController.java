package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.service.NotificationService;

@RequiredArgsConstructor
@RestController
public class NotificationApiController {
    private final NotificationService notificationService;

    @PutMapping("/api/v1/notification/{id}")
    public Long updateNotification(@PathVariable Long id){
        return notificationService.updateNotification(id);
    }
}
