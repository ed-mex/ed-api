package com.vincent.edmex.service;

import com.vincent.edmex.pojo.Room;
import com.vincent.edmex.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public List<Integer> getAllRoomId() {
        return roomRepository.getAllRoomId();
    }


    public Room getRoomById(Integer id) {
        return roomRepository.getRoomById(id);
    }

    public Boolean updateRoom(Room room) {
        return roomRepository.updateRoom(room);
    }

    public Boolean insertRoom(Room room) {
        return roomRepository.insertRoom(room);
    }
}
