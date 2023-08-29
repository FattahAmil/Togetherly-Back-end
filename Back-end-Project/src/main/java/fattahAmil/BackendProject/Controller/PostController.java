package fattahAmil.BackendProject.Controller;

import fattahAmil.BackendProject.Dto.MediaDto;
import fattahAmil.BackendProject.Dto.PostDto;
import fattahAmil.BackendProject.Entity.Media;
import fattahAmil.BackendProject.Entity.Post;
import fattahAmil.BackendProject.Entity.User;
import fattahAmil.BackendProject.Repository.UserRepository;
import fattahAmil.BackendProject.Service.Implement.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Autowired
    private UserRepository userRepository;



    @PostMapping("/create")
    public ResponseEntity<?> createPostWithMedia(@RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.createPostWithMedia(postDto));
    }
}
