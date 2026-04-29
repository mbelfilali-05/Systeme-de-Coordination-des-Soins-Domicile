package ma.ensam.soins.visit.service;

import ma.ensam.soins.visit.model.Visit;
import ma.ensam.soins.visit.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    public Optional<Visit> getVisitById(Long id) {
        return visitRepository.findById(id);
    }

    public Visit createVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    public Visit updateVisit(Long id, Visit details) {
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visite non trouvée: " + id));
        visit.setPatientId(details.getPatientId());
        visit.setCaregiverId(details.getCaregiverId());
        visit.setPatientNom(details.getPatientNom());
        visit.setCaregiverNom(details.getCaregiverNom());
        visit.setDateVisite(details.getDateVisite());
        visit.setHeureDebut(details.getHeureDebut());
        visit.setHeureFin(details.getHeureFin());
        visit.setStatut(details.getStatut());
        visit.setTypeSoin(details.getTypeSoin());
        visit.setNotes(details.getNotes());
        visit.setOrdrePassage(details.getOrdrePassage());
        return visitRepository.save(visit);
    }

    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);
    }

    public List<Visit> getVisitsByCaregiver(Long caregiverId, LocalDate date) {
        return visitRepository.findByCaregiverIdAndDateVisite(caregiverId, date);
    }

    public List<Visit> getVisitsByDate(LocalDate date) {
        return visitRepository.findByDateVisite(date);
    }

    public List<Visit> getVisitsByPatient(Long patientId) {
        return visitRepository.findByPatientId(patientId);
    }
}
