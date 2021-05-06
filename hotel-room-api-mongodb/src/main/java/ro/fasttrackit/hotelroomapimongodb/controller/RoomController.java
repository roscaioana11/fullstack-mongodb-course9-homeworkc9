package ro.fasttrackit.hotelroomapimongodb.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.hotelroomapimongodb.model.RoomFilters;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Room;
import ro.fasttrackit.hotelroomapimongodb.service.RoomService;

@RestController
@RequestMapping("rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    Page<Room> getFilteredRoomsOrAllRooms(RoomFilters filters){
        return roomService.getFilteredRooms(filters);
    }

    @GetMapping("{roomId}")
    Room getRoomById(@PathVariable String roomId){
        return roomService.getRoomById(roomId);
    }

    @PatchMapping(path = "/{roomId}")
    Room patchRoom(@RequestBody JsonPatch patch,
                   @PathVariable ("roomId") String roomId){
        return roomService.patchRoom(roomId, patch);
    }

    @DeleteMapping("{roomId}")
    void deleteRoom(@PathVariable String roomId){
        roomService.deleteRoom(roomId);
    }


//    @GetMapping("/{roomId}/cleanups")
//    List<Cleanup> getCleanupsForRoom(@PathVariable String roomId){
//        return cleanupService.getCleanupsForRoom(roomId);
//    }
}
