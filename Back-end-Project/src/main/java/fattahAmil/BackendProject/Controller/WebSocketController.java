package fattahAmil.BackendProject.Controller;

import fattahAmil.BackendProject.Dto.ChatDtoReq;
import fattahAmil.BackendProject.Dto.MessagePrivateDto;
import fattahAmil.BackendProject.Entity.ChatMessage;
import fattahAmil.BackendProject.Service.Implement.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class WebSocketController {

    private final ChatMessageService chatMessageService;



    @Autowired
   private SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/notification")
    private void PrivateNotification(@Payload ChatDtoReq chatNotification){
        String destination = "/user/" + chatNotification.getUserEmailReceiver() + "/privateMessage";
        simpMessagingTemplate.convertAndSend(destination, chatNotification);
    }

    @MessageMapping("/privateMessage")
    private void PrivateMessage(@Payload MessagePrivateDto messagePrivateDto){
        ChatMessage chatMessage=chatMessageService.createMessage(messagePrivateDto.getUserFromId(), messagePrivateDto.getRecipientId(), messagePrivateDto.getContent(), messagePrivateDto.getTypeMessage());
        String destination = "/user/" + chatMessage.getRecipient().getEmail() + "/privateMessage";
        simpMessagingTemplate.convertAndSend(destination, chatMessage);
    }


}
