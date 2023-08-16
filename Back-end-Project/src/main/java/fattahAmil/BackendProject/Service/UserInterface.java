package fattahAmil.BackendProject.Service;


import fattahAmil.BackendProject.Entity.Role;
import fattahAmil.BackendProject.Entity.User;

public interface UserInterface {
    User saveUser(User user);
    Role saveRole(Role role);
    void addToUser(String username,String roleName);
}
