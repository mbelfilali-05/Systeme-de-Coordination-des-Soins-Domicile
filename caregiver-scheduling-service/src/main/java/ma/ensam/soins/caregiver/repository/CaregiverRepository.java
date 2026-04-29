package ma.ensam.soins.caregiver.repository;

import ma.ensam.soins.caregiver.model.Caregiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaregiverRepository extends JpaRepository<Caregiver, Long> {
    List<Caregiver> findByDisponibleTrue();
    List<Caregiver> findBySpecialite(Caregiver.Specialite specialite);
    List<Caregiver> findByDisponibleTrueAndSpecialite(Caregiver.Specialite specialite);
}
