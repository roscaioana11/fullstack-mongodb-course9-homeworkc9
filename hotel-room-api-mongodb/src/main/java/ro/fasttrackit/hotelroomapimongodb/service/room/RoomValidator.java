package ro.fasttrackit.hotelroomapimongodb.service.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.hotelroomapimongodb.exception.ValidationException;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Room;
import ro.fasttrackit.hotelroomapimongodb.repository.RoomRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

@Component
@RequiredArgsConstructor
public class RoomValidator {
    private final RoomRepository repository;

//    public void validateNewThrow(Room newRoom) {
//        validate(newRoom, true)
//                .ifPresent(ex -> {
//                    throw ex;
//                });
//    }
//
//    public void validateReplaceThrow(String roomId, Room newRoom) {
//        exists(roomId)
//                .or(() -> validate(newRoom, false))
//                .ifPresent(ex -> {
//                    throw ex;
//                });
//    }
//
//    private Optional<ValidationException> validate(Room room, boolean newEntity) {
//        List<String> errors = new ArrayList<>();
//        if (room.getNumber() == null) {
//            errors.add("Number cannot be null");
//        }
////        if (newEntity && repository.existsByNumber(room.getNumber())) {
////            errors.add("Name cannot be duplicate");
////        }
////        if (!newEntity && repository.existsByNumberAndIdNot(room.getNumber(), room.getId())) {
////            errors.add("Name cannot be duplicate");
////        }
////        if(!countries.readCity().contains(room.getCity())){
////            errors.add("Restaurant not found in the provided city");
////        }
////        if(LocalDate.now().isBefore(room.getSince())) {
////            errors.add("Open since cannot be in the future");
////        }
//
//        return errors.isEmpty() ? empty() : Optional.of(new ValidationException(errors));
//    }

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
