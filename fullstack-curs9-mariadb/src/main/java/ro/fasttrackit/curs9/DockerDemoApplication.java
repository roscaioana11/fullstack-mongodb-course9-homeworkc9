package ro.fasttrackit.curs9;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ro.fasttrackit.curs9.model.entity.Room;
import ro.fasttrackit.curs9.repository.RoomRepository;

import java.util.List;

@SpringBootApplication
public class DockerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DockerDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner dataLoader(RoomRepository repository) {
        return args -> {
            repository.saveAll(List.of(
                    Room.builder()
                            .roomNumber("220A")
                            .build(),
                    Room.builder()
                            .roomNumber("220B")
                            .build(),
                    Room.builder()
                            .roomNumber("222A")
                            .build(), Room.builder()
                            .roomNumber("223")
                            .build()));
        };
    }
}


