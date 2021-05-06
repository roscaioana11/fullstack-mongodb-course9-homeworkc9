package ro.fasttrackit.hotelroomapimongodb.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "rooms")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
    @Id
    private String id;

    private String number;
    private int floor;
    private String hotelName;

    private RoomFacilities facilities;

    public Room(String number, int floor, String hotelName, RoomFacilities facilities) {
        this.number = number;
        this.floor = floor;
        this.hotelName = hotelName;
        this.facilities = facilities;
    }
}
