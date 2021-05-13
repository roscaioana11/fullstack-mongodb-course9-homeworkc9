package ro.fasttrackit.hotelroomapimongodb.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Room> getAll(RoomFilters filters, Pageable pageable) {

        Criteria criteria = new Criteria();

        ofNullable(filters.getNumber())
                .ifPresent(number -> criteria.and("number").is(number));
        ofNullable(filters.getFloor())
                .ifPresent(floor -> criteria.and("floor").is(floor));
        ofNullable(filters.getHotelName())
                .ifPresent(hotelName -> criteria.and("hotelName").is(hotelName));
        ofNullable(filters.getTv())
                .ifPresent(tv -> criteria.and("facilities.tv").is(tv));
        ofNullable(filters.getDoubleBed())
                .ifPresent(doubleBed -> criteria.and("facilities.doubleBed").is(doubleBed));

        Query query = Query.query(criteria).with(pageable); //query nu e immutabil, au schimbat sa fie cu paginare si fiind cu paginare, nr total de elemente o sa fie lungimea paginii, count o sa dea tot timpul lungimea paginii pt ca este restrictionat
        //query e construit o data paginat query si o data nepaginat
        List<Room> rooms = mongo.find(
                query,
                Room.class);
        return PageableExecutionUtils.getPage(
                rooms,
                pageable,
                () -> mongo.count(query.limit(-1).skip(-1), Room.class)); // sa reseteze paginarea
                        //(Query.query(criteria), Room.class));
    }
}
