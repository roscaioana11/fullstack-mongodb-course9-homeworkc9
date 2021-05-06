package ro.fasttrackit.hotelroomapimongodb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ro.fasttrackit.hotelroomapimongodb.exception.ResourceNotFoundException;
import ro.fasttrackit.hotelroomapimongodb.model.RoomFilters;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Room;
import ro.fasttrackit.hotelroomapimongodb.repository.RoomDao;
import ro.fasttrackit.hotelroomapimongodb.repository.RoomRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository repository;
    private final RoomDao dao;
    private final ObjectMapper mapper;

    public List<Room> getAllRooms(){
        return repository.findAll();
    }

    public Page<Room> getFilteredRooms(RoomFilters filters){
        return dao.getAll(filters);
    }

    public Room getRoomById(String roomId){
        return repository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find room with id " + roomId));
    }

    @SneakyThrows
    public Room patchRoom(String roomId, JsonPatch patch){
        Room dbRoom = repository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find room with id " + roomId));

        JsonNode patchedRoomJson = patch.apply(mapper.valueToTree(dbRoom));
        Room patchedRoom = mapper.treeToValue(patchedRoomJson, Room.class);
        return repository.save(patchedRoom);
    }

    public void deleteRoom(String roomId){
        repository.deleteById(roomId);
    }
}
