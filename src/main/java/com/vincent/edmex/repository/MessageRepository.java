package com.vincent.edmex.repository;

import com.vincent.edmex.pojo.Message;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.vincent.edmex.config.jooq.Tables.MESSAGE;

@Repository
public class MessageRepository {

    @Autowired
    DSLContext dsl;

    public Message getMessageById(Integer id) {

        List<Message> messages = dsl.select()
                .from(MESSAGE)
                .where(MESSAGE.IDROOM.eq(id))
                .fetchInto(Message.class);

        return messages.isEmpty() ? null : messages.get(0);
    }

}
