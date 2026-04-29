package ma.ensam.soins.billing;

import ma.ensam.soins.billing.model.Invoice;
import ma.ensam.soins.billing.repository.InvoiceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final InvoiceRepository invoiceRepository;

    public DataInitializer(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public void run(String... args) {
        if (invoiceRepository.count() == 0) {
            Invoice f1 = new Invoice();
            f1.setNumeroFacture("FACT-2025-001");
            f1.setPatientId(1L);
            f1.setPatientNom("Alaoui Fatima");
            f1.setCaregiverId(1L);
            f1.setCaregiverNom("Mansouri Rachid");
            f1.setDateSoin(LocalDate.now().minusDays(3));
            f1.setTypeSoin("Injection insuline + surveillance");
            f1.setMontantHT(250.0);
            f1.setTauxTVA(0.0);
            f1.setMontantTTC(250.0);
            f1.setStatut(Invoice.StatutFacture.PAYEE);
            f1.setModePaiement(Invoice.ModePaiement.ASSURANCE);
            f1.setReferenceAssurance("CNSS-2025-78432");
            f1.setPartAssurance(200.0);
            f1.setPartPatient(50.0);

            Invoice f2 = new Invoice();
            f2.setNumeroFacture("FACT-2025-002");
            f2.setPatientId(2L);
            f2.setPatientNom("Bennani Mohammed");
            f2.setCaregiverId(3L);
            f2.setCaregiverNom("Amrani Youssef");
            f2.setDateSoin(LocalDate.now().minusDays(1));
            f2.setTypeSoin("Séance kinésithérapie");
            f2.setMontantHT(350.0);
            f2.setTauxTVA(0.0);
            f2.setMontantTTC(350.0);
            f2.setStatut(Invoice.StatutFacture.EN_ATTENTE);
            f2.setModePaiement(Invoice.ModePaiement.MIXTE);
            f2.setReferenceAssurance("CNOPS-2025-11290");
            f2.setPartAssurance(280.0);
            f2.setPartPatient(70.0);

            Invoice f3 = new Invoice();
            f3.setNumeroFacture("FACT-2025-003");
            f3.setPatientId(3L);
            f3.setPatientNom("Chraibi Amina");
            f3.setCaregiverId(4L);
            f3.setCaregiverNom("Ouazzani Leila");
            f3.setDateSoin(LocalDate.now());
            f3.setTypeSoin("Soins palliatifs + perfusion");
            f3.setMontantHT(500.0);
            f3.setTauxTVA(0.0);
            f3.setMontantTTC(500.0);
            f3.setStatut(Invoice.StatutFacture.VALIDEE);
            f3.setModePaiement(Invoice.ModePaiement.ASSURANCE);
            f3.setReferenceAssurance("RMA-2025-55671");
            f3.setPartAssurance(450.0);
            f3.setPartPatient(50.0);

            invoiceRepository.save(f1);
            invoiceRepository.save(f2);
            invoiceRepository.save(f3);

            System.out.println(">>> 3 factures de démonstration créées avec succès");
        }
    }
}
