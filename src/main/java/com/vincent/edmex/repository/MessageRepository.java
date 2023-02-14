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

    public Message getLastMessage() {

        List<Message> messages = dsl.select()
                .from(MESSAGE)
                .orderBy(MESSAGE.CREATED_DATE.desc())
                .fetchInto(Message.class);

        return messages.isEmpty() ? null : messages.get(0);
    }

    public Boolean updateMessage(Message message) {
        return dsl.update(MESSAGE)
                .set(MESSAGE.SENDER, message.getSender())
                .set(MESSAGE.MESSAGE_, message.getMessage())
                .set(MESSAGE.CREATED_DATE, new java.sql.Timestamp(message.getCreatedDate().getTime()).toLocalDateTime())
                .where(MESSAGE.IDROOM.eq(message.getIdRoom())).execute() > 0;

    }

    public Boolean insertMessage(Message message) {
        return dsl.insertInto(MESSAGE)
                .set(MESSAGE.IDROOM, message.getIdRoom())
                .set(MESSAGE.SENDER, message.getSender())
                .set(MESSAGE.CREATED_DATE, new java.sql.Timestamp(message.getCreatedDate().getTime()).toLocalDateTime())
                .set(MESSAGE.MESSAGE_, message.getMessage()).execute() > 0;

    }


}
