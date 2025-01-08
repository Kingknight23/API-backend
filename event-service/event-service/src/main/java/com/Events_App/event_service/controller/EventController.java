package com.Events_App.event_service.controller;

import com.Events_App.event_service.dto.EventRequest;
import com.Events_App.event_service.dto.EventResponse;
import com.Events_App.event_service.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvent(@Validated @RequestBody EventRequest eventRequest){
        eventService.createEvent(eventRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventResponse> getAllEvents(){
        return eventService.getAllEvents();
    }
}
