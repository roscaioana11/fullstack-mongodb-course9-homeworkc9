package ro.fasttrackit.hotelroomapimongodb.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Cleanup;
import ro.fasttrackit.hotelroomapimongodb.service.CleanupService;

import java.util.List;

@RestController
@RequestMapping("rooms/{roomId}/cleanups")
@RequiredArgsConstructor
public class CleanupController {
    private final CleanupService cleanupService;

    @GetMapping
    List<Cleanup> getCleanupsForRoom(@PathVariable String roomId){
        return cleanupService.getCleanupsForRoom(roomId);
    }

    @PostMapping
    Cleanup createCleanup(@RequestBody Cleanup cleanup, @PathVariable String roomId){
        return cleanupService.createCleanup(roomId, cleanup);
    }

    @PatchMapping("/{cleanupId}")
    Cleanup patchCleanup(@RequestBody JsonPatch patch,
                         @PathVariable ("cleanupId") String cleanupId){
        return cleanupService.patchCleanup(cleanupId,patch);
    }

    @DeleteMapping("/{cleanupId}")
    void deleteCleanup(@PathVariable String cleanupId){
        cleanupService.deleteCleanup(cleanupId);
    }
}
