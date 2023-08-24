package fattahAmil.BackendProject.Service.Implement;

import fattahAmil.BackendProject.Entity.ChatMessage;
import fattahAmil.BackendProject.Entity.Post;
import fattahAmil.BackendProject.Entity.enm.MessageType;
import fattahAmil.BackendProject.Repository.ChatMessageRepository;
import fattahAmil.BackendProject.Service.ChatMessageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;

public class ChatMessageService implements ChatMessageInterface {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatMessageService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public void sendMessageToUser(String senderUsername, String recipientUsername, String content) {
        ChatMessage chatMessage = new ChatMessage(senderUsername, recipientUsername, content);
        chatMessageRepository.save(chatMessage);

        // Send the message to the recipient's private message queue
        String destination = "/user/" + recipientUsername + "/queue/privateMessages";
        messagingTemplate.convertAndSend(destination, chatMessage);
    }


    @Override
    public void deleteMessage(Long messageId) {
        chatMessageRepository.deleteById(messageId);
    }
}