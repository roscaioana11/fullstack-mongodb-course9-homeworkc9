package ro.fasttrackit.hotelroomapimongodb.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "room_facilities")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomFacilities {
    private boolean tv;
    private boolean doubleBed;
}
