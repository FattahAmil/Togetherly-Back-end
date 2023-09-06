package fattahAmil.BackendProject.Controller;

import fattahAmil.BackendProject.Dto.ChatDtoReq;
import fattahAmil.BackendProject.Entity.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatMessageController {

    @Autowired
   private SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/privateMessage")
    private void PrivateMessage(@Payload ChatDtoReq chatMessage){
        String destination = "/user/" + chatMessage.getUserEmailReceiver() + "/privateMessage";
        simpMessagingTemplate.convertAndSend(destination, chatMessage);
    }
}
