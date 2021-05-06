package ro.fasttrackit.hotelroomapimongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ro.fasttrackit.hotelroomapimongodb.model.entity.Room;
import ro.fasttrackit.hotelroomapimongodb.model.entity.RoomFacilities;
import ro.fasttrackit.hotelroomapimongodb.repository.RoomDao;
import ro.fasttrackit.hotelroomapimongodb.repository.RoomRepository;

import java.util.List;

@SpringBootApplication
public class HotelRoomApiMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelRoomApiMongodbApplication.class, args);
	}

//	@Bean
//	CommandLineRunner startup(RoomRepository repo, RoomDao dao){
//		return args -> {
//			repo.saveAll(List.of(
//					new Room("1A", 1, "Luna", new RoomFacilities(true, true)),
//					new Room("2A", 1, "Luna", new RoomFacilities(false, true)),
//					new Room("1B", 2, "Luna", new RoomFacilities(true, false)),
//					new Room("2B", 2, "Luna", new RoomFacilities(false, false)),
//					new Room("3A", 3, "Luna", new RoomFacilities(true, true))
//			));
//			System.out.println(dao.getAll());
//		};
//	}
}
