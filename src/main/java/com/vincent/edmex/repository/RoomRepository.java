package com.vincent.edmex.repository;

import com.vincent.edmex.pojo.Room;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.vincent.edmex.config.jooq.Tables.ROOM;

@Repository
public class RoomRepository {

    @Autowired
    DSLContext dsl;


    public List<Integer> getAllRoomId() {

        return  dsl.select(ROOM.IDROOM)
                .from(ROOM)
                .fetchInto(Integer.class);
    }


    public Room getRoomById(Integer id) {

        List<Room> rooms = dsl.select()
                .from(ROOM)
                .where(ROOM.IDROOM.eq(id))
                .fetchInto(Room.class);

        return rooms.isEmpty() ? null : rooms.get(0);
    }

    public Boolean updateRoom(Room room) {
        return dsl.update(ROOM)
                .set(ROOM.PATIENT_NAME, room.getPatientName())
                .set(ROOM.PATIENTE_SURNAME, room.getPatientSurname())
                .where(ROOM.IDROOM.eq(room.getIdRoom())).execute() > 0;

    }

    public Boolean insertRoom(Room room) {
        return dsl.insertInto(ROOM)
                .set(ROOM.IDROOM, room.getIdRoom())
                .set(ROOM.PATIENT_NAME, room.getPatientName())
                .set(ROOM.PATIENTE_SURNAME, room.getPatientSurname()).execute() > 0;

    }
}
