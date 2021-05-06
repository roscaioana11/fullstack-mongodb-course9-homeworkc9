package ro.fasttrackit.hotelroomapimongodb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ro.fasttrackit.hotelroomapimongodb.exception.ResourceNotFoundException;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Review;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Room;
import ro.fasttrackit.hotelroomapimongodb.repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final RoomService roomService;
    private final ReviewRepository reviewRepository;
    private final ObjectMapper mapper;

    public List<Review> getReviewsForRoom(String roomId){
        return reviewRepository.findByRoomId(roomId);
    }

    public Review createReview(String roomId, Review review) {
        //TODO validation
        Room room = roomService.getRoomById(roomId);
        review.setRoomId(room.getId());
        return reviewRepository.save(review);
    }

    @SneakyThrows
    public Review patchedReview(String reviewId, JsonPatch patch){
        Review dbReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find review with id " + reviewId));

        JsonNode patchedReviewJson = patch.apply(mapper.valueToTree(dbReview));
        Review patchedReview = mapper.treeToValue(patchedReviewJson, Review.class);
        return reviewRepository.save(patchedReview);
    }

    public void deleteReview(String reviewId){
        reviewRepository.deleteById(reviewId);
    }
}
