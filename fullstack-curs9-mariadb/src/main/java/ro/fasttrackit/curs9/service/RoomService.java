package ro.fasttrackit.curs9.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs9.model.entity.Room;
import ro.fasttrackit.curs9.repository.RoomRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public List<Room> getAll() {
        return roomRepository.findAll();
    }
}
