package ro.fasttrackit.hotelroomapimongodb.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomFacilities {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private boolean tv;
    private boolean doubleBed;
}
