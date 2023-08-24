package fattahAmil.BackendProject.Service.Implement;


import fattahAmil.BackendProject.Entity.Role;
import fattahAmil.BackendProject.Entity.User;
import fattahAmil.BackendProject.Repository.RoleRepository;
import fattahAmil.BackendProject.Repository.UserRepository;
import fattahAmil.BackendProject.Service.UserInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserInterface {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String id){
        return userRepository.findById(id);
    }
    @Override
    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addToUser(String username, String roleName) {
        if (!userRepository.findByEmail(username).isPresent()){
            throw new IllegalArgumentException("user email :"+username+" does not exists !");
        }
        Role role = roleRepository.findByName(roleName);
        if (role == null){
            throw new IllegalArgumentException("user role :"+roleName+" does not exists !");
        }
        User user=userRepository.findByEmail(username).get();
        user.getRoles().add(role);
    }

    @Override
    public User followUser(String followerId, String followedId) {
        User follower = userRepository.findById(followerId).orElse(null);
        User followed = userRepository.findById(followedId).orElse(null);

        if (follower != null && followed != null) {
            follower.getFollowing().add(followed);
            followed.getFollowers().add(follower);
            userRepository.save(follower);
            userRepository.save(followed);
            return follower;
        }

        return null;
    }

    @Override
    public Set<User> getFollowers(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return user.getFollowers();
        }
        return new HashSet<>();
    }
}
