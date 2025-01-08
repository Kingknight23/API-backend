package com.Events_App.event_service.service;

import com.Events_App.event_service.dto.EventRequest;
import com.Events_App.event_service.dto.EventResponse;
import com.Events_App.event_service.model.Event;
import com.Events_App.event_service.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {
    private final EventRepository eventRepository;



    public void createEvent(EventRequest eventRequest){
        try {
            Event event = Event.builder()
                    .title(eventRequest.getTitle())
                    .description(eventRequest.getDescription())
                    .price(eventRequest.getPrice())
                    .build();

            eventRepository.save(event);
            log.info("Event {} is created", event.getId());
        } catch (Exception e) {
            log.error("Error creating event: {}", eventRequest, e);
            throw new RuntimeException("Failed to create event", e);
        }
    }

    public List<EventResponse> getAllEvents() {
        log.info("Fetching all events");
        List<Event> events = eventRepository.findAll();

        return events.stream().map(this::mapToEventResponse).toList();
    }
    public Page<EventResponse> getAllEvents(Pageable pageable) {
        Page<Event> events = eventRepository.findAll(pageable);
        return events.map(this::mapToEventResponse);
    }

    private EventResponse mapToEventResponse(Event event)
    {
        return EventResponse.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .price(event.getPrice())
                .build();
    }


}
