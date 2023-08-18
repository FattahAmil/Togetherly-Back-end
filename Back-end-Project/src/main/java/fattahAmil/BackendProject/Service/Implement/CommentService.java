package fattahAmil.BackendProject.Service.Implement;

import fattahAmil.BackendProject.Entity.Comment;
import fattahAmil.BackendProject.Entity.Post;
import fattahAmil.BackendProject.Entity.User;
import fattahAmil.BackendProject.Repository.CommentRepository;
import fattahAmil.BackendProject.Repository.PostRepository;
import fattahAmil.BackendProject.Repository.UserRepository;
import fattahAmil.BackendProject.Service.CommentInetrface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements CommentInetrface {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Long postId, Comment comment) throws ChangeSetPersister.NotFoundException {
        Post post = postRepository.findById(postId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        comment.setPost(post);
        // Set other properties for the comment

        return commentRepository.save(comment);
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
