package ro.fasttrackit.hotelroomapimongodb.service.review;

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
import ro.fasttrackit.hotelroomapimongodb.service.room.RoomService;
import ro.fasttrackit.hotelroomapimongodb.service.room.RoomValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final RoomService roomService;
    private final RoomValidator roomValidator;
    private final ReviewValidator reviewValidator;
    private final ReviewRepository reviewRepository;
    private final ObjectMapper mapper;

    public List<Review> getReviewsForRoom(String roomId){
        return reviewRepository.findByRoomId(roomId);
    }

    public Review createReview(String roomId, Review review) {
        roomValidator.validateExistsOrThrow(roomId);
        Room room = roomService.getRoomById(roomId);
        review.setRoomId(room.getId());
        return reviewRepository.save(review);
    }

    @SneakyThrows
    public Review patchedReview(String reviewId, JsonPatch patch){
        reviewValidator.validateExistsOrThrow(reviewId);
        Review dbReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find review with id " + reviewId));

        JsonNode patchedReviewJson = patch.apply(mapper.valueToTree(dbReview));
        Review patchedReview = mapper.treeToValue(patchedReviewJson, Review.class);
        return reviewRepository.save(patchedReview);
    }

    public void deleteReview(String reviewId){
        reviewValidator.validateExistsOrThrow(reviewId);
        reviewRepository.deleteById(reviewId);
    }
}
