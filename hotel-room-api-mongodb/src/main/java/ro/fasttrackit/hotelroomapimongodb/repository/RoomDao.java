package ro.fasttrackit.hotelroomapimongodb.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.hotelroomapimongodb.model.RoomFilters;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Room;

import java.util.List;

import static java.util.Optional.ofNullable;

@Repository
@RequiredArgsConstructor
public class RoomDao {
    private final MongoTemplate mongo;

    public Page<Room> getAll(RoomFilters filters) {

        Query query = new Query();

        ofNullable(filters.getNumber())
                .ifPresent(number -> query.addCriteria(Criteria.where("number").is(number)));
        ofNullable(filters.getFloor())
                .ifPresent(floor -> query.addCriteria(Criteria.where("floor").is(floor)));
        ofNullable(filters.getHotelName())
                .ifPresent(hotelName -> query.addCriteria(Criteria.where("hotelName").is(hotelName)));
        ofNullable(filters.getTv())
                .ifPresent(tv -> query.addCriteria(Criteria.where("facilities.tv").is(tv)));
        ofNullable(filters.getDoubleBed())
                .ifPresent(doubleBed -> query.addCriteria(Criteria.where("facilities.doubleBed").is(doubleBed)));

        PageRequest pageable = PageRequest.of(0, 10, Sort.by("roomNumber"));

        query.with(pageable);
        List<Room> rooms = mongo.find(
                query,
                Room.class);
        return PageableExecutionUtils.getPage(
                rooms,
                pageable,
                () -> mongo.count(query, Room.class));
    }
}
