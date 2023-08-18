package fattahAmil.BackendProject.Service;

import fattahAmil.BackendProject.Entity.Media;
import fattahAmil.BackendProject.Entity.Post;
import fattahAmil.BackendProject.Entity.User;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface PostInterface {
    Post createPostWithMedia(Post post, List<Media> mediaList);
    void deletePost(Long postId,User user) throws Exception;
    Post updatePost(Long postId, Post updatedPost,User user) throws Exception;
    List<Post> getAllPosts();
    List<Post> getPostsByUser(User user);

}
