package ro.fasttrackit.hotelroomapimongodb.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.hotelroomapimongodb.model.RoomFilters;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Cleanup;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Room;
import ro.fasttrackit.hotelroomapimongodb.service.CleanupService;
import ro.fasttrackit.hotelroomapimongodb.service.RoomService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final CleanupService cleanupService;

    @GetMapping
    List<Room> getFilteredRoomsOrAllRooms(RoomFilters filters){
        return roomService.getFilteredRooms(filters);
    }

    @GetMapping("{roomId}")
    Optional<Room> getRoomById(@PathVariable long roomId){
        return roomService.getRoomById(roomId);
    }

    @PatchMapping(path = "/{roomId}")
    Room patchRoom(@RequestBody JsonPatch patch,
                   @PathVariable ("roomId") long roomId){
        return roomService.patchRoom(roomId, patch);
    }

    @DeleteMapping("{roomId}")
    void deleteRoom(@PathVariable long roomId){
        roomService.deleteRoom(roomId);
    }
}
