package ro.fasttrackit.hotelroomapimongodb.model;

import lombok.Value;

@Value
public class RoomFilters {
    String number;
    Integer floor;
    String hotelName;
    Boolean tv;
    Boolean doubleBed;
}
