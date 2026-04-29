package ma.ensam.soins.caregiver.service;

import ma.ensam.soins.caregiver.model.Caregiver;
import ma.ensam.soins.caregiver.repository.CaregiverRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaregiverService {

    private final CaregiverRepository caregiverRepository;

    public CaregiverService(CaregiverRepository caregiverRepository) {
        this.caregiverRepository = caregiverRepository;
    }

    public List<Caregiver> getAllCaregivers() {
        return caregiverRepository.findAll();
    }

    public Optional<Caregiver> getCaregiverById(Long id) {
        return caregiverRepository.findById(id);
    }

    public Caregiver createCaregiver(Caregiver caregiver) {
        return caregiverRepository.save(caregiver);
    }

    public Caregiver updateCaregiver(Long id, Caregiver details) {
        Caregiver caregiver = caregiverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Soignant non trouvé: " + id));

        caregiver.setNom(details.getNom());
        caregiver.setPrenom(details.getPrenom());
        caregiver.setTelephone(details.getTelephone());
        caregiver.setSpecialite(details.getSpecialite());
        caregiver.setLatitudeBase(details.getLatitudeBase());
        caregiver.setLongitudeBase(details.getLongitudeBase());
        caregiver.setDisponible(details.isDisponible());
        caregiver.setCompetences(details.getCompetences());
        caregiver.setZoneCouverture(details.getZoneCouverture());
        caregiver.setCapaciteJournaliere(details.getCapaciteJournaliere());

        return caregiverRepository.save(caregiver);
    }

    public void deleteCaregiver(Long id) {
        caregiverRepository.deleteById(id);
    }

    public List<Caregiver> getAvailableCaregivers() {
        return caregiverRepository.findByDisponibleTrue();
    }

    public List<Caregiver> getAvailableBySpecialite(Caregiver.Specialite specialite) {
        return caregiverRepository.findByDisponibleTrueAndSpecialite(specialite);
    }
}
