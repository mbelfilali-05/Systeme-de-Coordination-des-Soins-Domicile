package ma.ensam.soins.tracking.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tracking_events")
public class TrackingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long visitId;
    private Long caregiverId;
    private Long patientId;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private LocalDateTime timestamp;

    private Double latitude;
    private Double longitude;

    private String details;
    private Integer retardMinutes; // null si pas de retard

    public enum EventType {
        DEPART, EN_ROUTE, ARRIVEE, DEBUT_SOIN, FIN_SOIN, INCIDENT, RETARD
    }

    public TrackingEvent() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getVisitId() { return visitId; }
    public void setVisitId(Long visitId) { this.visitId = visitId; }
    public Long getCaregiverId() { return caregiverId; }
    public void setCaregiverId(Long caregiverId) { this.caregiverId = caregiverId; }
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public EventType getEventType() { return eventType; }
    public void setEventType(EventType eventType) { this.eventType = eventType; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
    public Integer getRetardMinutes() { return retardMinutes; }
    public void setRetardMinutes(Integer retardMinutes) { this.retardMinutes = retardMinutes; }
}
