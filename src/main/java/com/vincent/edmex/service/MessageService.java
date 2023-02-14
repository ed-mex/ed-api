package com.vincent.edmex.service;

import com.vincent.edmex.pojo.Message;
import com.vincent.edmex.repository.MessageBCKRepository;
import com.vincent.edmex.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageBCKRepository messageBCKRepository;

    public Message getMessageById(Integer id) {
        return messageRepository.getMessageById(id);
    }

    public Message getLastMessage(Integer idRoom, boolean isAdmin) {
        return Optional.ofNullable(getMessageById(idRoom))
                .orElse(isAdmin ? messageRepository.getLastMessage() :
                        Message.builder().sender("NESSUN ULTIMO MESSAGGIO")
                                .createdDate(new Date())
                                .message("NESSUN ULTIMO MESSAGGIO").build());
    }


    public Boolean setMessage(Message message) {
        messageBCKRepository.insertMessageBCK(message);
        return messageRepository.updateMessage(message);
    }
}
