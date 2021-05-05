package ro.fasttrackit.hotelroomapimongodb.repository;

import org.springframework.stereotype.Repository;
import ro.fasttrackit.hotelroomapimongodb.model.RoomFilters;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Room;
import ro.fasttrackit.hotelroomapimongodb.model.entity.RoomFacilities;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Repository
public class RoomDao {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public RoomDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        criteriaBuilder = this.entityManager.getCriteriaBuilder();
    }

    public List<Room> getAll(RoomFilters filters){
        CriteriaQuery<Room> criteria = criteriaBuilder.createQuery(Room.class);
        Root<Room> rootRoom = criteria.from(Room.class);
        Root<RoomFacilities> rootRoomFacilities = criteria.from(RoomFacilities.class);

        List<Predicate> whereClause = new ArrayList<>();
        ofNullable(filters.getNumber())
                .ifPresent(number -> whereClause.add(criteriaBuilder.equal(rootRoom.get("number"), number)));
        ofNullable(filters.getFloor())
                .ifPresent(floor -> whereClause.add(criteriaBuilder.equal(rootRoom.get("floor"), floor)));
        ofNullable(filters.getHotelName())
                .ifPresent(hotelName -> whereClause.add(criteriaBuilder.equal(rootRoom.get("hotelName"), hotelName)));
        ofNullable(filters.getTv())
                .ifPresent(tv -> whereClause.add(criteriaBuilder.equal(rootRoomFacilities.get("tv"), tv)));
        ofNullable(filters.getDoubleBed())
                .ifPresent(doubleBed -> whereClause.add(criteriaBuilder.equal(rootRoomFacilities.get("doubleBed"), doubleBed)));

        CriteriaQuery<Room> query = criteria.select(rootRoom).where(whereClause.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
