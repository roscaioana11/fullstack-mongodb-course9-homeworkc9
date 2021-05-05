package ro.fasttrackit.hotelroomapimongodb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
