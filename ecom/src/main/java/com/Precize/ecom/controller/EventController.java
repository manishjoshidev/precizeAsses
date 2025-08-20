package com.Precize.ecom.controller;

import com.Precize.ecom.entity.Event;
import com.Precize.ecom.repository.EventRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }


    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }


    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id " + id));
    }
}
