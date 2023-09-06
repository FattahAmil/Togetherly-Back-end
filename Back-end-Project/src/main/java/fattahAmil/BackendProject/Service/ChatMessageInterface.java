package fattahAmil.BackendProject.Service;

import fattahAmil.BackendProject.Entity.ChatMessage;
import fattahAmil.BackendProject.Entity.User;

public interface ChatMessageInterface {
    void sendMessageToUser(User senderUsername, User recipientUsername, String content);

    void deleteMessage(Long messageId);

}
