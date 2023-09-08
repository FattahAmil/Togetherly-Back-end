package fattahAmil.BackendProject.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessagePrivateDto {

    String userFromId;
    String recipientId;
    String content;
    String typeMessage;
}
