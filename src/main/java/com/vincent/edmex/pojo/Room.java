package com.vincent.edmex.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Room {
    Integer idRoom;
    String patientName;
    String patientSurname;
}
