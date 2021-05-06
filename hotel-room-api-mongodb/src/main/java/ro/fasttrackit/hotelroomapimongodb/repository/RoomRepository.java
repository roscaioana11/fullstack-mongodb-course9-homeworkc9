package ro.fasttrackit.hotelroomapimongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Room;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {
}
