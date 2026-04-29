package ma.ensam.soins.caregiver;

import ma.ensam.soins.caregiver.model.Caregiver;
import ma.ensam.soins.caregiver.repository.CaregiverRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CaregiverRepository caregiverRepository;

    public DataInitializer(CaregiverRepository caregiverRepository) {
        this.caregiverRepository = caregiverRepository;
    }

    @Override
    public void run(String... args) {
        if (caregiverRepository.count() == 0) {
            Caregiver c1 = new Caregiver("Mansouri", "Rachid", Caregiver.Specialite.INFIRMIER, 34.0105, -6.8370);
            c1.setTelephone("0670123456");
            c1.setCompetences("Injections, pansements, prises de sang, surveillance glycémie");
            c1.setZoneCouverture("Rabat Centre, Agdal");
            c1.setCapaciteJournaliere(10);

            Caregiver c2 = new Caregiver("Tahiri", "Samira", Caregiver.Specialite.AIDE_SOIGNANT, 34.0400, -6.8100);
            c2.setTelephone("0671234567");
            c2.setCompetences("Toilette, habillage, aide repas, mobilisation");
            c2.setZoneCouverture("Salé, Rabat Nord");
            c2.setCapaciteJournaliere(8);

            Caregiver c3 = new Caregiver("Amrani", "Youssef", Caregiver.Specialite.KINESITHERAPEUTE, 33.9700, -6.8600);
            c3.setTelephone("0672345678");
            c3.setCompetences("Rééducation motrice, drainage lymphatique, massage thérapeutique");
            c3.setZoneCouverture("Hay Riad, Témara");
            c3.setCapaciteJournaliere(6);

            Caregiver c4 = new Caregiver("Ouazzani", "Leila", Caregiver.Specialite.INFIRMIER, 33.9900, -6.8550);
            c4.setTelephone("0673456789");
            c4.setCompetences("Chimiothérapie orale, perfusions, soins palliatifs");
            c4.setZoneCouverture("Agdal, Hay Riad, Souissi");
            c4.setCapaciteJournaliere(7);

            caregiverRepository.save(c1);
            caregiverRepository.save(c2);
            caregiverRepository.save(c3);
            caregiverRepository.save(c4);

            System.out.println(">>> 4 soignants de démonstration créés avec succès");
        }
    }
}
