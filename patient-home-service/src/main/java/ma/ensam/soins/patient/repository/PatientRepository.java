package ma.ensam.soins.patient.repository;

import ma.ensam.soins.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNiveauUrgence(Patient.NiveauUrgence niveauUrgence);
    List<Patient> findByNomContainingIgnoreCase(String nom);
}
