package fattahAmil.BackendProject.Service.Implement;

import fattahAmil.BackendProject.Entity.Media;
import fattahAmil.BackendProject.Entity.Post;
import fattahAmil.BackendProject.Entity.User;
import fattahAmil.BackendProject.Repository.CommentRepository;
import fattahAmil.BackendProject.Repository.LikeRepository;
import fattahAmil.BackendProject.Repository.PostRepository;
import fattahAmil.BackendProject.Service.PostInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements PostInterface {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private LikeRepository likeRepository;


    @Override
    public Post createPostWithMedia(Post post, List<Media> mediaList) {
        post.getMediaList().addAll(mediaList);
        return postRepository.save(post);
    }
    @Override
    public void deletePost(Long postId,User user) throws Exception {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        if (!existingPost.getUser().equals(user)||user.getRoles().stream().noneMatch(role -> role.getName().equals("ROLE_ADMIN"))){
            throw new Exception("this is not you post and you are not an admin");
        }

        likeRepository.deleteByPostId(postId);

        commentRepository.deleteByPostId(postId);

        postRepository.deleteById(postId);

    }

    @Override
    public Post updatePost(Long postId, Post updatedPost,User user) throws Exception {

        Post existingPost = postRepository.findById(postId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        if (!existingPost.getUser().equals(user) || user.getRoles().stream().noneMatch(role -> role.getName().equals("ROLE_ADMIN"))){
            throw new Exception("this is not you post and you are not an admin");
        }



        existingPost.setContent(updatedPost.getContent());

        if (!updatedPost.getMediaList().isEmpty()) {
            existingPost.getMediaList().clear();
            existingPost.getMediaList().addAll(updatedPost.getMediaList());
        }

        return postRepository.save(existingPost);
    }


    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getPostsByUser(User user) {
        return postRepository.findByUser(user);
    }


}
