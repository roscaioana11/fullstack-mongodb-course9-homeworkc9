package ro.fasttrackit.curs9.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.fasttrackit.curs9.model.entity.Room;
import ro.fasttrackit.curs9.service.RoomService;

import java.util.List;

@RequestMapping("rooms")
@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomService service;

    @GetMapping
    List<Room> getAll() {
        return service.getAll();
    }
}
