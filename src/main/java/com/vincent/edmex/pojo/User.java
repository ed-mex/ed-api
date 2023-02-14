package com.vincent.edmex.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {
    public User(User user) {
        this.setIdUser(user.getIdUser());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setRole(user.getRole());
        this.setEnabled(user.isEnabled());
        this.setIdRoom(user.getIdRoom());
    }

    Integer idUser;
    String username;
    String password;
    String role;
    boolean enabled;

    Integer idRoom;
}
