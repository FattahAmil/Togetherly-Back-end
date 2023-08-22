package fattahAmil.BackendProject.Service;


import fattahAmil.BackendProject.Entity.Role;
import fattahAmil.BackendProject.Entity.User;

import java.util.Set;

public interface UserInterface {
    User saveUser(User user);
    Role saveRole(Role role);
    void addToUser(String username,String roleName);
    User followUser(String followerId, String followedId);
    Set<User> getFollowers(String userId);
}
