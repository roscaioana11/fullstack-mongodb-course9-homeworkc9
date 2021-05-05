package ro.fasttrackit.hotelroomapimongodb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
