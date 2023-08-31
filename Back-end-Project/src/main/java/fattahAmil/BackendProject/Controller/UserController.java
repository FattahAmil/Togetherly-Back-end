package fattahAmil.BackendProject.Controller;


import fattahAmil.BackendProject.Dto.FollowDto;
import fattahAmil.BackendProject.Repository.FollowRelationRepository;
import fattahAmil.BackendProject.Service.Implement.FollowService;
import fattahAmil.BackendProject.Service.Implement.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final FollowService followService;
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PostMapping("/follow")
    public ResponseEntity<?> followUser(@RequestBody FollowDto followDto){
        return ResponseEntity.ok(followService.follow(followDto));
    }

    @GetMapping("/notFollowed/{id}")
    public ResponseEntity<?> notFollowed(@PathVariable("id")String id){
        return ResponseEntity.ok(followService.notFollowed(id));
    }
}
