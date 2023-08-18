package fattahAmil.BackendProject.Repository;

import fattahAmil.BackendProject.Entity.Like;
import fattahAmil.BackendProject.Entity.Post;
import fattahAmil.BackendProject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    List<Like> findByPost(Post post);
    List<Like> findByUsers(User user);

    void deleteByPostId(Long postId);

    boolean existsByPostAndUsers(Post post, User user);

    Long findIdByPostAndUsers(Post post, User user);

    List<Like> findByPostId(Long postId);
}
