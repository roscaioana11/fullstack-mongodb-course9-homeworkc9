package ro.fasttrackit.hotelroomapimongodb.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Review;
import ro.fasttrackit.hotelroomapimongodb.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("rooms/{roomId}/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    List<Review> getReviewsForRoom(@PathVariable String roomId){
        return reviewService.getReviewsForRoom(roomId);
    }

    @PostMapping
    Review createReview(@RequestBody Review review, @PathVariable String roomId){
        return reviewService.createReview(roomId, review);
    }

    @PatchMapping("/{reviewId}")
    Review patchedReview(@RequestBody JsonPatch patch,
                          @PathVariable ("reviewId") String reviewId){
        return reviewService.patchedReview(reviewId,patch);
    }

    @DeleteMapping("/{reviewId}")
    void deleteReview(@PathVariable String reviewId){
        reviewService.deleteReview(reviewId);
    }
}
