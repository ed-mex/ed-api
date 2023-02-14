package com.vincent.edmex.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@Builder
@ToString
public class Message {
    Integer idRoom;
    String sender;
    String message;
    Date createdDate;

}
