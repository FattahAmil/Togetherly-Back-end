package fattahAmil.BackendProject.Entity;

import fattahAmil.BackendProject.Entity.enm.MessageType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ChatMessage")
public class ChatMessage {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private MessageType type;
        private String content;
        private String sender;
        private String recipient;
        private LocalDateTime timestamp;

    public ChatMessage(  String sender, String recipient, String content) {
        this.type = MessageType.CHAT;
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
        this.timestamp = LocalDateTime.now();
    }
}

