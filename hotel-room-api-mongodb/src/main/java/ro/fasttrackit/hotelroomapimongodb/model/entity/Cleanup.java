package ro.fasttrackit.hotelroomapimongodb.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "cleanups")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cleanup {
    @Id
    private String id;

    private LocalDate date;

    private String roomId;
}
