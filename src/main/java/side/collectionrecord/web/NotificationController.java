package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import side.collectionrecord.service.NotificationService;
import side.collectionrecord.web.dto.GetNotificationResponseDto;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/notification/{userId}")
    public String notification(@PathVariable("userId") Long userId, Model model){

        List<GetNotificationResponseDto> notReadNotification = notificationService.getAllNotificationByUserIdReadFalse(userId);

        model.addAttribute("notifications", notReadNotification);

        return "notification/notification";
    }
}
