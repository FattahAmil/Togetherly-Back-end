package fattahAmil.BackendProject.Service;

import fattahAmil.BackendProject.Entity.User;
import fattahAmil.BackendProject.Entity.Notification;

import java.util.List;

public interface NotificationInterface {
    Notification createNotification(User Sender,User recipient, String message);
    List<Notification> getNotificationsForUser(User user);
    public Notification markNotificationAsRead(Long notificationId);
    public Notification markNotificationAsUnread(Long notificationId);

    }