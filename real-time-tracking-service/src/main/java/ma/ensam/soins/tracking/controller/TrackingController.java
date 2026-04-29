package ma.ensam.soins.tracking.controller;

import ma.ensam.soins.tracking.model.TrackingEvent;
import ma.ensam.soins.tracking.service.TrackingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracking")
@CrossOrigin(origins = "*")
public class TrackingController {

    private final TrackingService trackingService;

    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping
    public List<TrackingEvent> getAllEvents() {
        return trackingService.getAllEvents();
    }

    @PostMapping
    public ResponseEntity<TrackingEvent> logEvent(@RequestBody TrackingEvent event) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trackingService.logEvent(event));
    }

    @GetMapping("/visit/{visitId}")
    public List<TrackingEvent> getEventsByVisit(@PathVariable Long visitId) {
        return trackingService.getEventsByVisit(visitId);
    }

    @GetMapping("/caregiver/{caregiverId}")
    public List<TrackingEvent> getEventsByCaregiver(@PathVariable Long caregiverId) {
        return trackingService.getEventsByCaregiver(caregiverId);
    }

    @GetMapping("/incidents")
    public List<TrackingEvent> getIncidents() {
        return trackingService.getIncidents();
    }

    @GetMapping("/retards")
    public List<TrackingEvent> getRetards() {
        return trackingService.getRetards();
    }
}
