package ro.fasttrackit.hotelroomapimongodb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Cleanup;
import ro.fasttrackit.hotelroomapimongodb.service.CleanupService;
import ro.fasttrackit.hotelroomapimongodb.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("rooms/{roomId}/cleanups")
@RequiredArgsConstructor
public class CleanupController {
    private final RoomService roomService;
    private final CleanupService cleanupService;

    @GetMapping
    List<Cleanup> getCleanupsForRoom(@PathVariable long roomId){
        return cleanupService.getCleanupsForRoom(roomId);
    }
}
