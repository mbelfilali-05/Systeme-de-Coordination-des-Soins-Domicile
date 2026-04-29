package ma.ensam.soins.patient.service;

import ma.ensam.soins.patient.model.Patient;
import ma.ensam.soins.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'id: " + id));

        patient.setNom(patientDetails.getNom());
        patient.setPrenom(patientDetails.getPrenom());
        patient.setTelephone(patientDetails.getTelephone());
        patient.setAdresse(patientDetails.getAdresse());
        patient.setLatitude(patientDetails.getLatitude());
        patient.setLongitude(patientDetails.getLongitude());
        patient.setBesoins(patientDetails.getBesoins());
        patient.setContraintes(patientDetails.getContraintes());
        patient.setNiveauUrgence(patientDetails.getNiveauUrgence());

        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public List<Patient> getPatientsByUrgence(Patient.NiveauUrgence urgence) {
        return patientRepository.findByNiveauUrgence(urgence);
    }

    public List<Patient> searchPatients(String nom) {
        return patientRepository.findByNomContainingIgnoreCase(nom);
    }
}
