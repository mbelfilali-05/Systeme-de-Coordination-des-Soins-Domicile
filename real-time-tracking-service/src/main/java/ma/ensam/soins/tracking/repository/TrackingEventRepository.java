package ma.ensam.soins.tracking.repository;

import ma.ensam.soins.tracking.model.TrackingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingEventRepository extends JpaRepository<TrackingEvent, Long> {
    List<TrackingEvent> findByVisitIdOrderByTimestampDesc(Long visitId);
    List<TrackingEvent> findByCaregiverIdOrderByTimestampDesc(Long caregiverId);
    List<TrackingEvent> findByEventType(TrackingEvent.EventType eventType);
}
