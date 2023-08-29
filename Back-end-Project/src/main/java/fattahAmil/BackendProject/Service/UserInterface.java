package fattahAmil.BackendProject.Service;


import fattahAmil.BackendProject.Entity.Role;
import fattahAmil.BackendProject.Entity.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

public interface UserInterface {
    User saveUser(User user);

    Optional<User> getUserById(String id);

    ResponseEntity<?> getUserByEmail(String email);

    Role saveRole(Role role);
    void addToUser(String username,String roleName);
    User followUser(String followerId, String followedId);
    Set<User> getFollowers(String userId);


}
