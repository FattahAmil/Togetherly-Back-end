package fattahAmil.BackendProject.Service;

import fattahAmil.BackendProject.Dto.FollowDto;

import org.springframework.http.ResponseEntity;

public interface FollowInterface {

    public ResponseEntity<?> notFollowed(String id);
    public ResponseEntity<?> follow(FollowDto followDto);
}
