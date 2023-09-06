package fattahAmil.BackendProject.Entity;

import fattahAmil.BackendProject.Entity.enm.NotificationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private LocalDateTime timestamp;

    private NotificationType notificationType;

    private Boolean isRead;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private User userFrom;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private User recipient;

}
