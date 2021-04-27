package ro.fasttrackit.curs9.mongo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.fasttrackit.curs9.mongo.model.entity.Room;
import ro.fasttrackit.curs9.mongo.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService service;

    @GetMapping
    List<Room> getAll(@RequestParam(required = false) String number) {
        return service.getAll(number);
    }
}
