package com.trilogyed.notequeueconsumer;


import com.trilogyed.notequeueconsumer.util.messages.Note;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {
    @RabbitListener(queues = NoteQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage(Note msg){
        System.out.println(msg.toString());
    }
}
