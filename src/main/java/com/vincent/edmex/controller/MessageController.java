package com.vincent.edmex.controller;

import com.vincent.edmex.pojo.Message;
import com.vincent.edmex.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final Logger logger = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    MessageService messageService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ESP')")
    public Message getMessage(@PathVariable("id") Integer id) {
        return messageService.getMessageById(id);
    }

}
