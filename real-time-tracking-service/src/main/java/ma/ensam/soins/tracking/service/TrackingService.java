package ma.ensam.soins.tracking.service;

import ma.ensam.soins.tracking.model.TrackingEvent;
import ma.ensam.soins.tracking.repository.TrackingEventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrackingService {

    private final TrackingEventRepository repository;

    public TrackingService(TrackingEventRepository repository) {
        this.repository = repository;
    }

    public List<TrackingEvent> getAllEvents() {
        return repository.findAll();
    }

    public TrackingEvent logEvent(TrackingEvent event) {
        if (event.getTimestamp() == null) {
            event.setTimestamp(LocalDateTime.now());
        }
        return repository.save(event);
    }

    public List<TrackingEvent> getEventsByVisit(Long visitId) {
        return repository.findByVisitIdOrderByTimestampDesc(visitId);
    }

    public List<TrackingEvent> getEventsByCaregiver(Long caregiverId) {
        return repository.findByCaregiverIdOrderByTimestampDesc(caregiverId);
    }

    public List<TrackingEvent> getIncidents() {
        return repository.findByEventType(TrackingEvent.EventType.INCIDENT);
    }

    public List<TrackingEvent> getRetards() {
        return repository.findByEventType(TrackingEvent.EventType.RETARD);
    }
}
