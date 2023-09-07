package fattahAmil.BackendProject.Service.Implement;

import fattahAmil.BackendProject.Dto.CommentDto;
import fattahAmil.BackendProject.Entity.Comment;
import fattahAmil.BackendProject.Entity.Notification;
import fattahAmil.BackendProject.Entity.Post;
import fattahAmil.BackendProject.Entity.User;
import fattahAmil.BackendProject.Entity.enm.NotificationType;
import fattahAmil.BackendProject.Repository.CommentRepository;
import fattahAmil.BackendProject.Repository.NotificationRepository;
import fattahAmil.BackendProject.Repository.PostRepository;
import fattahAmil.BackendProject.Repository.UserRepository;
import fattahAmil.BackendProject.Service.CommentInetrface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class CommentService implements CommentInetrface {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationRepository notificationRepository;


    @Autowired
    private PostRepository postRepository;

    @Override
    public ResponseEntity<?> createComment(CommentDto commentDto) {
        try {
            User user = userRepository.findById(commentDto.getIdUser())
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);

            Post post = postRepository.findById(commentDto.getIdPost())
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);

            Comment comment=new Comment();
            comment.setUsers(user);
            comment.setPost(post);
            comment.setContent(commentDto.getContent());
            commentRepository.save(comment);
            if (!Objects.equals(user.getId(), post.getUser().getId())){
                Notification notification=new Notification();
                notification.setUserFrom(user);
                notification.setIsRead(false);
                notification.setRecipient(post.getUser());
                notification.setMessage(user.getFirstName()+" "+user.getLastName()+" comment your Post your post");
                notification.setNotificationType(NotificationType.COMMENT);
                notification.setIdPost(post.getId());
                notificationRepository.save(notification);
            }
            // Set other properties for the comment

            return ResponseEntity.ok("comment has been created");
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @Override
    public void updateComment(Long commentId, Comment updatedComment, User user) throws Exception {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        // || user.getRoles().stream().noneMatch(role -> role.getName().equals("ROLE_ADMIN"))  for check a role
    if (!existingComment.getUsers().equals(user) || user.getRoles().stream().noneMatch(role -> role.getName().equals("ROLE_ADMIN"))) {
            throw new Exception("You are not authorized to update this comment and you are not an admin");
        }


        existingComment.setContent(updatedComment.getContent());


        commentRepository.save(existingComment);
    }


    @Override
    public void deleteComment(Long commentId,User user) throws Exception {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        // || user.getRoles().stream().noneMatch(role -> role.getName().equals("ROLE_ADMIN"))  for check a role
        if (!existingComment.getUsers().equals(user) || user.getRoles().stream().noneMatch(role -> role.getName().equals("ROLE_ADMIN"))) {
            throw new Exception("You are not authorized to update this comment and you are not an admin");
        }
        commentRepository.deleteById(commentId);

    }

    @Override
    public List<Comment> getCommentsForPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public List<Comment> getCommentsByUser(User user) {
        return commentRepository.findByUsers(user);
    }
}
