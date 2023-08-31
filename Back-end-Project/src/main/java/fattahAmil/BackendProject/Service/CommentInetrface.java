package fattahAmil.BackendProject.Service;

import fattahAmil.BackendProject.Dto.CommentDto;
import fattahAmil.BackendProject.Entity.Comment;
import fattahAmil.BackendProject.Entity.User;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentInetrface {
    ResponseEntity<?> createComment(CommentDto commentDto);
    void updateComment(Long commentId, Comment updatedComment, User user) throws Exception ;
    void deleteComment(Long commentId,User user) throws Exception;
    List<Comment> getCommentsForPost(Long postId);
    List<Comment> getCommentsByUser(User user);
}
