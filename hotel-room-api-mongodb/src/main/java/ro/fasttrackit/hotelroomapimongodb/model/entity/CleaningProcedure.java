package ro.fasttrackit.hotelroomapimongodb.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cleaning_procedures")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CleaningProcedure {
    @Id
    private String id;

    private String name;
    private int outcome;

    private String cleanupId;
}
