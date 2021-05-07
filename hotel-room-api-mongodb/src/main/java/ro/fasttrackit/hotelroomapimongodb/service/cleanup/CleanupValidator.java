package ro.fasttrackit.hotelroomapimongodb.service.cleanup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.hotelroomapimongodb.exception.ValidationException;
import ro.fasttrackit.hotelroomapimongodb.repository.CleanupRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

@Component
@RequiredArgsConstructor
public class CleanupValidator {
    private final CleanupRepository repository;

    private Optional<ValidationException> exists(String cleanupId) {
        return repository.existsById(cleanupId)
                ? empty()
                : Optional.of(new ValidationException(List.of("Cleanup with id " + cleanupId + " does not exist.")));
    }

    public void validateExistsOrThrow(String cleanupId) {
        exists(cleanupId).ifPresent(ex -> {
            throw ex;
        });
    }
}
