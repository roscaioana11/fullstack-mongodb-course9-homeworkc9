package ro.fasttrackit.hotelroomapimongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Cleanup;

import java.util.List;

@Repository
public interface CleanupRepository extends MongoRepository<Cleanup, String> {
    List<Cleanup> findByRoomId(String roomId);
}
