package fattahAmil.BackendProject.Service.Implement;

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
    public Like likeUnlikePost(Long postId, String userId) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        Post post = postRepository.findById(postId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        // Check if the user has already liked the post and if like unlike the post
        if (likeRepository.existsByPostAndUsers(post, user)) {
            likeRepository.deleteById(likeRepository.findIdByPostAndUsers(post,user));
        }

        Like like = new Like();
        like.setUsers(user);
        like.setPost(post);

        return likeRepository.save(like);
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
