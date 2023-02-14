package com.vincent.edmex.repository;

import com.vincent.edmex.pojo.User;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<User> getAllUsers() {

        List<User> users = dsl.select()
                .from(USERS)
                .orderBy(USERS.IDUSER.desc())
                .fetch(record -> new User(
                        record.get(USERS.IDUSER, Integer.class),
                        record.get(USERS.USERNAME, String.class),
                        record.get(USERS.PASSWORD, String.class),
                        record.get(USERS.ROLE, String.class),
                        record.get(USERS.ENABLED, Boolean.class),
                        record.get(USERS.IDROOM, Integer.class))
                );

        users.stream().peek(user -> user.setPassword("***")).collect(Collectors.toList());

        return users;
    }

    public Boolean addNewUser(User newUser) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        newUser.setPassword(encoder.encode(newUser.getPassword()));

        return dsl.insertInto(USERS)
                .set(USERS.USERNAME, newUser.getUsername())
                .set(USERS.PASSWORD, newUser.getPassword())
                .set(USERS.ROLE, newUser.getRole())
                .set(USERS.ENABLED, (byte) (newUser.isEnabled() ? 1 : 0))
                .set(USERS.IDROOM, newUser.getIdRoom())
                .execute() < 1;

    }

}
