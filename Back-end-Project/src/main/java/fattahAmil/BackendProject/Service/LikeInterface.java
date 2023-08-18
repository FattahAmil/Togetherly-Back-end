package fattahAmil.BackendProject.Service;

import fattahAmil.BackendProject.Entity.Like;
import fattahAmil.BackendProject.Entity.Post;
import fattahAmil.BackendProject.Entity.User;

import java.util.List;

public interface LikeInterface {
    Like likeUnlikePost(Long postId, String userId) throws Exception;
    void unlikePost(Long likeId) throws Exception;
    List<Like> getLikesForPost(Post post);
    List<Like> getLikesByUser(User user);

}
