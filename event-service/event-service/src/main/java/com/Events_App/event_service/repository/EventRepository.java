package com.Events_App.event_service.repository;

import com.Events_App.event_service.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
}
