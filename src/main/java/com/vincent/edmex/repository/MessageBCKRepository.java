package com.vincent.edmex.repository;

import com.vincent.edmex.pojo.Message;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.vincent.edmex.config.jooq.Tables.MESSAGE;
import static com.vincent.edmex.config.jooq.Tables.MESSAGE_BCK;

@Repository
public class MessageBCKRepository {

    @Autowired
    DSLContext dsl;

    public Message getMessageBCKById(Integer id) {

        List<Message> messages = dsl.select()
                .from(MESSAGE_BCK)
                .where(MESSAGE_BCK.IDROOM.eq(id))
                .fetchInto(Message.class);

        return messages.isEmpty() ? null : messages.get(0);
    }

    public Message getLastMessageBCK() {

        List<Message> messages = dsl.select()
                .from(MESSAGE_BCK)
                .orderBy(MESSAGE_BCK.CREATED_DATE.desc())
                .fetchInto(Message.class);

        return messages.isEmpty() ? null : messages.get(0);
    }

    public Boolean updateMessageBCK(Message message) {
        return dsl.update(MESSAGE_BCK)
                .set(MESSAGE_BCK.SENDER, message.getSender())
                .set(MESSAGE_BCK.MESSAGE, message.getMessage())
                .set(MESSAGE_BCK.CREATED_DATE, new java.sql.Timestamp(message.getCreatedDate().getTime()).toLocalDateTime())
                .where(MESSAGE_BCK.IDROOM.eq(message.getIdRoom())).execute() > 0;

    }

    public Boolean insertMessageBCK(Message message) {
        return dsl.insertInto(MESSAGE_BCK)
                .set(MESSAGE_BCK.IDROOM, message.getIdRoom())
                .set(MESSAGE_BCK.SENDER, message.getSender())
                .set(MESSAGE_BCK.CREATED_DATE, new java.sql.Timestamp(message.getCreatedDate().getTime()).toLocalDateTime())
                .set(MESSAGE_BCK.MESSAGE, message.getMessage()).execute() > 0;

    }


}
