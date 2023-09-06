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

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private User sender;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private User recipient;

    private LocalDateTime timestamp;


}

