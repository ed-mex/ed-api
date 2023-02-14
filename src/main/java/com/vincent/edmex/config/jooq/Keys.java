/*
 * This file is generated by jOOQ.
 */
package com.vincent.edmex.config.jooq;


import com.vincent.edmex.config.jooq.tables.Message;
import com.vincent.edmex.config.jooq.tables.Room;
import com.vincent.edmex.config.jooq.tables.Users;
import com.vincent.edmex.config.jooq.tables.records.MessageRecord;
import com.vincent.edmex.config.jooq.tables.records.RoomRecord;
import com.vincent.edmex.config.jooq.tables.records.UsersRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in 
 * ed_mex.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<MessageRecord> KEY_MESSAGE_IDROOM_UNIQUE = Internal.createUniqueKey(Message.MESSAGE, DSL.name("KEY_message_idRoom_UNIQUE"), new TableField[] { Message.MESSAGE.IDROOM }, true);
    public static final UniqueKey<MessageRecord> KEY_MESSAGE_PRIMARY = Internal.createUniqueKey(Message.MESSAGE, DSL.name("KEY_message_PRIMARY"), new TableField[] { Message.MESSAGE.IDROOM }, true);
    public static final UniqueKey<RoomRecord> KEY_ROOM_PATIENT_IDX = Internal.createUniqueKey(Room.ROOM, DSL.name("KEY_room_patient_idx"), new TableField[] { Room.ROOM.IDROOM }, true);
    public static final UniqueKey<UsersRecord> KEY_USERS_PRIMARY = Internal.createUniqueKey(Users.USERS, DSL.name("KEY_users_PRIMARY"), new TableField[] { Users.USERS.IDUSER }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<MessageRecord, RoomRecord> IDROOM = Internal.createForeignKey(Message.MESSAGE, DSL.name("idroom"), new TableField[] { Message.MESSAGE.IDROOM }, Keys.KEY_ROOM_PATIENT_IDX, new TableField[] { Room.ROOM.IDROOM }, true);
    public static final ForeignKey<UsersRecord, RoomRecord> FK_IDROOM = Internal.createForeignKey(Users.USERS, DSL.name("fk_idroom"), new TableField[] { Users.USERS.IDROOM }, Keys.KEY_ROOM_PATIENT_IDX, new TableField[] { Room.ROOM.IDROOM }, true);
}
