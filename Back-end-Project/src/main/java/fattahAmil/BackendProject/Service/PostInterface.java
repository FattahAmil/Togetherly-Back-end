package fattahAmil.BackendProject.Service;

import fattahAmil.BackendProject.Dto.PostDto;
import fattahAmil.BackendProject.Entity.Media;
import fattahAmil.BackendProject.Entity.Post;
import fattahAmil.BackendProject.Entity.User;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostInterface {
    ResponseEntity<?> createPostWithMedia(PostDto postDto);

    Post createPostWithMedia(Post post, List<Media> mediaList);

    void deletePost(Long postId, User user) throws Exception;
    Post updatePost(Long postId, Post updatedPost,User user) throws Exception;
    List<Post> getAllPosts();
    List<Post> getPostsByUser(User user);

}
