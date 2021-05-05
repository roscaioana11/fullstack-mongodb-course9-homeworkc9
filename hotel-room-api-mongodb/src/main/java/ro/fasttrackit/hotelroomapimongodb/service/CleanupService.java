package ro.fasttrackit.hotelroomapimongodb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Cleanup;
import ro.fasttrackit.hotelroomapimongodb.repository.CleanupRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CleanupService {
    private final CleanupRepository cleanupRepository;

    public List<Cleanup> getCleanupsForRoom(long roomId){
        return cleanupRepository.findByRoomIdJPQL(roomId);
    }
}
