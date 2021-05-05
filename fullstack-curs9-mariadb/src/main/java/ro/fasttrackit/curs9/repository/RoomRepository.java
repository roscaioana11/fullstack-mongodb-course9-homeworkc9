package ro.fasttrackit.curs9.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.curs9.model.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
