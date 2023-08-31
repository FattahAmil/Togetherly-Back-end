package fattahAmil.BackendProject.Controller;

import fattahAmil.BackendProject.Dto.CommentDto;
import fattahAmil.BackendProject.Dto.LikeDto;
import fattahAmil.BackendProject.Dto.MediaDto;
import fattahAmil.BackendProject.Dto.PostDto;
import fattahAmil.BackendProject.Entity.Media;
import fattahAmil.BackendProject.Entity.Post;
import fattahAmil.BackendProject.Entity.User;
import fattahAmil.BackendProject.Repository.UserRepository;
import fattahAmil.BackendProject.Service.Implement.CommentService;
import fattahAmil.BackendProject.Service.Implement.LikeService;
import fattahAmil.BackendProject.Service.Implement.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final LikeService likeService;

    private final CommentService commentService;

    @Autowired
    private UserRepository userRepository;



    @PostMapping("/create")
    public ResponseEntity<?> createPostWithMedia(@RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.createPostWithMedia(postDto));
    }

    @GetMapping("/PostByFollowingUser")
    public ResponseEntity<?> ShowPostByFollowingUser(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping("/comment")
    public ResponseEntity<?> commentPost(@RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.createComment(commentDto));
    }
    @PostMapping("/like")
    public ResponseEntity<?> likeUnlikePost(@RequestBody LikeDto likeDto){
        return ResponseEntity.ok(likeService.likeUnlikePost(likeDto));
    }

    @GetMapping("/PostUser/{id}")
    public ResponseEntity<?> findPostsAndUsersByUserAndFollowingUsers(@PathVariable("id")String id){
        return ResponseEntity.ok(postService.getPostAndUsersByUserAndFollowedUser(id));
    }
}
