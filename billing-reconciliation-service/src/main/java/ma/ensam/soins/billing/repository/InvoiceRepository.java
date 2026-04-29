package ma.ensam.soins.billing.repository;

import ma.ensam.soins.billing.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findByNumeroFacture(String numeroFacture);
    List<Invoice> findByPatientId(Long patientId);
    List<Invoice> findByCaregiverId(Long caregiverId);
    List<Invoice> findByStatut(Invoice.StatutFacture statut);
    List<Invoice> findByDateFactureBetween(LocalDate debut, LocalDate fin);

    @Query("SELECT SUM(i.montantTTC) FROM Invoice i WHERE i.statut = 'PAYEE'")
    Double getTotalRevenuPaye();

    @Query("SELECT SUM(i.montantTTC) FROM Invoice i WHERE i.statut = 'EN_ATTENTE'")
    Double getTotalEnAttente();
}
