package fattahAmil.BackendProject.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    String id;
    String firstName;
    String lastName;
    String userName;
    String email;
    String profileImage;
}
