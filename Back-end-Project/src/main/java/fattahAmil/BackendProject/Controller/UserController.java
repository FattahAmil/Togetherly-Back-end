package fattahAmil.BackendProject.Controller;


import fattahAmil.BackendProject.Service.Implement.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

}
