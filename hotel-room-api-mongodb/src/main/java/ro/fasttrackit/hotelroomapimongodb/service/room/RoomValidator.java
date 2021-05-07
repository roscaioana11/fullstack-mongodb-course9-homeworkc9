package ro.fasttrackit.hotelroomapimongodb.service.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.hotelroomapimongodb.exception.ValidationException;
import ro.fasttrackit.hotelroomapimongodb.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

@Component
@RequiredArgsConstructor
public class RoomValidator {
    private final RoomRepository repository;

    private Optional<ValidationException> exists(String roomId) {
        return repository.existsById(roomId)
                ? empty()
                : Optional.of(new ValidationException(List.of("Room with id " + roomId + " doesn't exist")));
    }

    public void validateExistsOrThrow(String roomId) {
        exists(roomId).ifPresent(ex -> {
            throw ex;
        });
    }
}
