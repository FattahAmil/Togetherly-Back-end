package fattahAmil.BackendProject.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Comment")
public class Comment {
    @PrePersist
    protected void onCreate(){
        this.created_at=new Date(System.currentTimeMillis());
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComment;

    private String content;

    @ManyToOne
    @JoinColumn(referencedColumnName = "idPost")
    private Post post;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User users ;

    private Date created_at;


}
