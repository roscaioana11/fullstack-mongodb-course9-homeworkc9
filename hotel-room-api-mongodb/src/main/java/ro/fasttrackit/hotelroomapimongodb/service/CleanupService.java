package ro.fasttrackit.hotelroomapimongodb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ro.fasttrackit.hotelroomapimongodb.exception.ResourceNotFoundException;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Cleanup;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Room;
import ro.fasttrackit.hotelroomapimongodb.repository.CleanupRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CleanupService {
    private final RoomService roomService;
    private final CleanupRepository cleanupRepository;
    private final ObjectMapper mapper;

    public List<Cleanup> getCleanupsForRoom(String roomId){
        return cleanupRepository.findByRoomId(roomId);
    }

    public Cleanup createCleanup(String roomId, Cleanup cleanup) {
        //TODO validation
        Room room = roomService.getRoomById(roomId);
        cleanup.setRoomId(room.getId());
        return cleanupRepository.save(cleanup);
    }

    @SneakyThrows
    public Cleanup patchCleanup(String cleanupId,JsonPatch patch){
       Cleanup dbCleanup = cleanupRepository.findById(cleanupId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find cleanup with id " + cleanupId));

        JsonNode patchedCleanupJson = patch.apply(mapper.valueToTree(dbCleanup));
        Cleanup patchedCleanup = mapper.treeToValue(patchedCleanupJson, Cleanup.class);
        return cleanupRepository.save(patchedCleanup);
    }

    public void deleteCleanup(String cleanupId){
        cleanupRepository.deleteById(cleanupId);
    }
}
