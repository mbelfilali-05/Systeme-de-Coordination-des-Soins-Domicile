package ma.ensam.soins.visit.repository;

import ma.ensam.soins.visit.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByCaregiverIdAndDateVisite(Long caregiverId, LocalDate dateVisite);
    List<Visit> findByDateVisite(LocalDate dateVisite);
    List<Visit> findByPatientId(Long patientId);
    List<Visit> findByStatut(Visit.StatutVisite statut);
}
