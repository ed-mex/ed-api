package com.vincent.edmex.service;

import com.vincent.edmex.pojo.Message;
import com.vincent.edmex.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public Message getMessageById(Integer id) {
        return messageRepository.getMessageById(id);
    }

}
