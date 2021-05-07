package ro.fasttrackit.hotelroomapimongodb.service.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.hotelroomapimongodb.exception.ValidationException;
import ro.fasttrackit.hotelroomapimongodb.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

@Component
@RequiredArgsConstructor
public class ReviewValidator {
    private final ReviewRepository repository;

    private Optional<ValidationException> exists(String reviewId) {
        System.out.println(repository.findAll());
        return repository.existsById(reviewId)
                ? empty()
                : Optional.of(new ValidationException(List.of("Review with id " + reviewId + " does not exist.")));
    }

    public void validateExistsOrThrow(String reviewId) {
        exists(reviewId).ifPresent(ex -> {
            throw ex;
        });
    }
}
