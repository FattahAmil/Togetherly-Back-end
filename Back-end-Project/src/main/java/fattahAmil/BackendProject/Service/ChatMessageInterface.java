package fattahAmil.BackendProject.Service;

import fattahAmil.BackendProject.Entity.ChatMessage;

public interface ChatMessageInterface {
    void sendMessageToUser(String senderUsername, String recipientUsername, String content);

    void deleteMessage(Long messageId);

}
