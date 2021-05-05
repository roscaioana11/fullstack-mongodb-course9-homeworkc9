package ro.fasttrackit.hotelroomapimongodb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ro.fasttrackit.hotelroomapimongodb.model.RoomFilters;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Room;
import ro.fasttrackit.hotelroomapimongodb.repository.RoomDao;
import ro.fasttrackit.hotelroomapimongodb.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository repository;
    private final RoomDao dao;
    private final ObjectMapper mapper;

    public List<Room> getAllRooms(){
        return repository.findAll();
    }

    public List<Room> getFilteredRooms(RoomFilters filters){
        return dao.getAll(filters);
    }

    public Optional<Room> getRoomById(long roomId){
        return repository.findById(roomId);
    }


    @SneakyThrows
    public Room patchRoom(long roomId, JsonPatch patch){
        Optional<Room> dbRoom = repository.findById(roomId);

        JsonNode patchedRoomJson = patch.apply(mapper.valueToTree(dbRoom));
        Room patchedRoom = mapper.treeToValue(patchedRoomJson, Room.class);
        return repository.save(patchedRoom);
    }

    public void deleteRoom(long roomId){
        repository.findById(roomId);
    }
}
