package fattahAmil.BackendProject.Service.Implement;

import fattahAmil.BackendProject.Entity.User;
import fattahAmil.BackendProject.Repository.NotificationRepository;
import fattahAmil.BackendProject.Service.NotificationInterface;
import fattahAmil.BackendProject.Entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationService implements NotificationInterface {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification createNotification(User recipient, String message) {
        Notification notification = new Notification();
        notification.setRecipient(recipient);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());
        notification.setIsRead(false);
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotificationsForUser(User user) {
        return notificationRepository.findByRecipientOrderByTimestampDesc(user);
    }
    @Override
    public Notification markNotificationAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElse(null);
        if (notification != null) {
            notification.setIsRead(true);
            return notificationRepository.save(notification);
        }
        return null;
    }
    @Override
    public Notification markNotificationAsUnread(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElse(null);
        if (notification != null) {
            notification.setIsRead(false);
            return notificationRepository.save(notification);
        }
        return null;
    }
}
