package ro.fasttrackit.hotelroomapimongodb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.fasttrackit.hotelroomapimongodb.service.ReviewService;
import ro.fasttrackit.hotelroomapimongodb.service.RoomService;

@RestController
@RequestMapping("rooms/{roomId}/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final RoomService roomService;
    private final ReviewService reviewService;


}
