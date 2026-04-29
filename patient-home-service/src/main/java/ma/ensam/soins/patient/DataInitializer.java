package ma.ensam.soins.patient;

import ma.ensam.soins.patient.model.Patient;
import ma.ensam.soins.patient.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PatientRepository patientRepository;

    public DataInitializer(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) {
        if (patientRepository.count() == 0) {
            Patient p1 = new Patient("Alaoui", "Fatima", "12 Rue Hassan II, Rabat", 34.0209, -6.8416);
            p1.setTelephone("0661234567");
            p1.setBesoins("Soins infirmiers quotidiens, prise de tension, injection insuline");
            p1.setContraintes("Disponible uniquement le matin avant 11h");
            p1.setNiveauUrgence(Patient.NiveauUrgence.URGENT);

            Patient p2 = new Patient("Bennani", "Mohammed", "45 Avenue Mohamed V, Salé", 34.0531, -6.7985);
            p2.setTelephone("0662345678");
            p2.setBesoins("Rééducation post-opératoire, kinésithérapie");
            p2.setContraintes("Accès fauteuil roulant nécessaire");
            p2.setNiveauUrgence(Patient.NiveauUrgence.NORMAL);

            Patient p3 = new Patient("Chraibi", "Amina", "8 Rue Oukaimeden, Agdal", 33.9923, -6.8498);
            p3.setTelephone("0663456789");
            p3.setBesoins("Soins palliatifs, gestion douleur, surveillance constantes");
            p3.setContraintes("Présence aidant obligatoire, visite après 14h");
            p3.setNiveauUrgence(Patient.NiveauUrgence.CRITIQUE);

            Patient p4 = new Patient("Drissi", "Karim", "22 Rue Sebou, Hay Riad", 33.9575, -6.8693);
            p4.setTelephone("0664567890");
            p4.setBesoins("Pansements chroniques, soins de plaie");
            p4.setContraintes("Aucune contrainte particulière");
            p4.setNiveauUrgence(Patient.NiveauUrgence.FAIBLE);

            Patient p5 = new Patient("El Fassi", "Nadia", "3 Boulevard Tariq Ibn Ziad, Témara", 33.9267, -6.9073);
            p5.setTelephone("0665678901");
            p5.setBesoins("Perfusion à domicile, chimiothérapie orale surveillance");
            p5.setContraintes("Visite entre 9h et 12h uniquement");
            p5.setNiveauUrgence(Patient.NiveauUrgence.URGENT);

            patientRepository.save(p1);
            patientRepository.save(p2);
            patientRepository.save(p3);
            patientRepository.save(p4);
            patientRepository.save(p5);

            System.out.println(">>> 5 patients de démonstration créés avec succès");
        }
    }
}
