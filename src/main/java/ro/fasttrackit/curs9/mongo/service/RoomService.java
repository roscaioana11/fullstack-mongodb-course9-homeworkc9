package ro.fasttrackit.curs9.mongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs9.mongo.model.entity.Room;
import ro.fasttrackit.curs9.mongo.repository.RoomRepository;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public List<Room> getAll(String number) {
        return ofNullable(number)
                .map(roomRepository::findByRoomNumber)
                .orElseGet(roomRepository::findAll);
    }
}
