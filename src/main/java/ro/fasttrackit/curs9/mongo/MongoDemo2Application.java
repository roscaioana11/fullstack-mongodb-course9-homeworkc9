package ro.fasttrackit.curs9.mongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ro.fasttrackit.curs9.mongo.model.entity.Room;
import ro.fasttrackit.curs9.mongo.repository.RoomDao;
import ro.fasttrackit.curs9.mongo.repository.RoomRepository;

import java.util.List;

@SpringBootApplication
public class MongoDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(MongoDemo2Application.class, args);
	}

	@Bean
	CommandLineRunner startup(RoomRepository repo, RoomDao dao){
		return args -> {
			repo.saveAll(List.of(
					new Room("22C"),
					new Room("23A"),
					new Room("3")
					));
			System.out.println(dao.findRooms());
		};
	}
}
