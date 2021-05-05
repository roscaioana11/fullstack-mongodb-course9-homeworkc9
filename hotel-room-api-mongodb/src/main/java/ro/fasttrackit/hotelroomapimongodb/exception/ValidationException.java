package ro.fasttrackit.hotelroomapimongodb.exception;

import java.util.List;

public class ValidationException extends RuntimeException{
    public ValidationException(List<String> errors){
        super("Validation errors: " + String.join(",", errors));
    }
}
