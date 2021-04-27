package ro.fasttrackit.curs9.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.curs9.mongo.model.entity.Room;

import java.util.List;

public interface RoomRepository extends MongoRepository<Room, String> {

    List<Room> findByRoomNumber(String roomNumber);
}
