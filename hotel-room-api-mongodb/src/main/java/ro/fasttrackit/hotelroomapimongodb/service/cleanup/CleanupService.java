package ro.fasttrackit.hotelroomapimongodb.service.cleanup;

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
import ro.fasttrackit.hotelroomapimongodb.service.room.RoomService;
import ro.fasttrackit.hotelroomapimongodb.service.room.RoomValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CleanupService {
    private final RoomService roomService;
    private final RoomValidator roomValidator;
    private final CleanupValidator cleanupValidator;
    private final CleanupRepository cleanupRepository;
    private final ObjectMapper mapper;

    public List<Cleanup> getCleanupsForRoom(String roomId){
        return cleanupRepository.findByRoomId(roomId);
    }

    public Cleanup createCleanup(String roomId, Cleanup cleanup) {
        roomValidator.validateExistsOrThrow(roomId);
        Room room = roomService.getRoomById(roomId);
        cleanup.setRoomId(room.getId());
        return cleanupRepository.save(cleanup);
    }

    @SneakyThrows
    public Cleanup patchCleanup(String cleanupId,JsonPatch patch){
        cleanupValidator.validateExistsOrThrow(cleanupId);
        Cleanup dbCleanup = cleanupRepository.findById(cleanupId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find cleanup with id " + cleanupId));

        JsonNode patchedCleanupJson = patch.apply(mapper.valueToTree(dbCleanup));
        Cleanup patchedCleanup = mapper.treeToValue(patchedCleanupJson, Cleanup.class);
        return cleanupRepository.save(patchedCleanup);
    }

    public void deleteCleanup(String cleanupId){
        cleanupValidator.validateExistsOrThrow(cleanupId);
        cleanupRepository.deleteById(cleanupId);
    }
}
