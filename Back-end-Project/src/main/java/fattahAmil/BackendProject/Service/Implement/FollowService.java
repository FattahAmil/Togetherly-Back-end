package fattahAmil.BackendProject.Service.Implement;

import fattahAmil.BackendProject.Dto.FollowDto;
import fattahAmil.BackendProject.Entity.FollowRelation;
import fattahAmil.BackendProject.Entity.User;
import fattahAmil.BackendProject.Repository.FollowRelationRepository;
import fattahAmil.BackendProject.Repository.UserRepository;
import fattahAmil.BackendProject.Service.FollowInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FollowService implements FollowInterface {
    @Autowired
    private FollowRelationRepository followRelationshipRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> notFollowed(String id){
        return ResponseEntity.ok(followRelationshipRepository.findUsersNotFollowedBy(id));
    }
    @Override
    public ResponseEntity<?> findUserFriend(String id){
        return ResponseEntity.ok(followRelationshipRepository.findUserFriend(id));
    }
    @Override
    public ResponseEntity<?> follow(FollowDto followDto) {
        try{
            if (!userRepository.findById(followDto.getFollowed()).isPresent()){
                throw new IllegalArgumentException("follower does not exists !");
            }
            if (!userRepository.findById(followDto.getFollowing()).isPresent()){
                throw new IllegalArgumentException("followed does not exists !");
            }
            User follower = userRepository.findById(followDto.getFollowing()).get();
            User followed = userRepository.findById(followDto.getFollowed()).get();
            Optional<FollowRelation> follow=followRelationshipRepository.findByFollowerAndFollowed(follower,followed);
             if (!follow.isPresent()) {
                 FollowRelation relationship = new FollowRelation();
                 relationship.setFollower(follower);
                 relationship.setFollowed(followed);
                 followRelationshipRepository.save(relationship);
                 return ResponseEntity.ok("has been followed");
             }
                 followRelationshipRepository.deleteById(follow.get().getId());
            return ResponseEntity.ok("has been unfollowed");
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
