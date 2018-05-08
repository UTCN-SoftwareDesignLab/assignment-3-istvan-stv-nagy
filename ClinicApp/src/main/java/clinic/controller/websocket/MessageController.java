package clinic.controller.websocket;

import clinic.dto.ConsultationDTO;
import clinic.entity.message.Greeting;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(ConsultationDTO consultation) throws Exception {
        return new Greeting("New Consultation: " + consultation.getDate() + " at:" + consultation.getStartTime() + "-" + consultation.getEndTime());
    }
}
