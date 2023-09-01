package fattahAmil.BackendProject.Service.Implement;

import fattahAmil.BackendProject.Dto.LikeDto;
import fattahAmil.BackendProject.Entity.Like;
import fattahAmil.BackendProject.Entity.Post;
import fattahAmil.BackendProject.Entity.User;
import fattahAmil.BackendProject.Repository.LikeRepository;
import fattahAmil.BackendProject.Repository.PostRepository;
import fattahAmil.BackendProject.Repository.UserRepository;
import fattahAmil.BackendProject.Service.LikeInterface;
import fattahAmil.BackendProject.Service.PostInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService implements LikeInterface {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public ResponseEntity<?> likeUnlikePost(LikeDto likeDto)  {
        try {
            User user = userRepository.findById(likeDto.getIdUser())
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);

            Post post = postRepository.findById(likeDto.getIdPost())
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);

            // Check if the user has already liked the post and if like unlike the post
            if (likeRepository.existsByPostAndUsers(post, user)) {
                long idPost=likeRepository.findIdByPostAndUsers(user.getId(),post.getId());
                likeRepository.deleteById(idPost);

                return ResponseEntity.ok(null);
            }

            Like like = new Like();
            like.setUsers(user);
            like.setPost(post);
            likeRepository.save(like);
            return ResponseEntity.ok(like);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @Override
    public void unlikePost(Long likeId) throws Exception {
            likeRepository.deleteById(likeId);
    }

    @Override
    public List<Like> getLikesForPost(Post post) {
        return likeRepository.findByPost(post);
    }

    @Override
    public List<Like> getLikesByUser(User user) {
        return likeRepository.findByUsers(user);
    }
}
