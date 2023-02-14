package com.vincent.edmex.repository;

import com.vincent.edmex.pojo.User;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.vincent.edmex.config.jooq.Tables.USERS;

@Service
public class UserRepository {

    @Autowired
    DSLContext dsl;

    public User getUserFromUsername(String username) {

        return dsl.select()
                .from(USERS)
                .where(USERS.USERNAME.eq(username))
                .fetch(record -> new User(
                        record.get(USERS.IDUSER, Integer.class),
                        record.get(USERS.USERNAME, String.class),
                        record.get(USERS.PASSWORD, String.class),
                        record.get(USERS.ROLE, String.class),
                        record.get(USERS.ENABLED, Boolean.class),
                        record.get(USERS.IDROOM, Integer.class))
                ).stream().findFirst().orElse(null);
    }

}
