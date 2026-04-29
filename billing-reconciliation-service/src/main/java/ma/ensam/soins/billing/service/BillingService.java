package ma.ensam.soins.billing.service;

import ma.ensam.soins.billing.dto.BillingStats;
import ma.ensam.soins.billing.model.Invoice;
import ma.ensam.soins.billing.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BillingService {

    private final InvoiceRepository invoiceRepository;

    public BillingService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Optional<Invoice> getInvoiceByNumero(String numero) {
        return invoiceRepository.findByNumeroFacture(numero);
    }

    public Invoice createInvoice(Invoice invoice) {
        // Auto-calculer montantTTC si non fourni
        if (invoice.getMontantHT() != null && invoice.getMontantTTC() == null) {
            double tva = invoice.getTauxTVA() != null ? invoice.getTauxTVA() : 0.0;
            invoice.setMontantTTC(invoice.getMontantHT() * (1 + tva));
        }
        // Calculer part patient = montant - part assurance
        if (invoice.getMontantTTC() != null && invoice.getPartAssurance() != null) {
            invoice.setPartPatient(invoice.getMontantTTC() - invoice.getPartAssurance());
        } else if (invoice.getMontantTTC() != null) {
            invoice.setPartPatient(invoice.getMontantTTC());
        }
        return invoiceRepository.save(invoice);
    }

    public Invoice updateStatut(Long id, Invoice.StatutFacture statut) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facture non trouvée: " + id));
        invoice.setStatut(statut);
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    public List<Invoice> getInvoicesByPatient(Long patientId) {
        return invoiceRepository.findByPatientId(patientId);
    }

    public List<Invoice> getInvoicesByCaregiver(Long caregiverId) {
        return invoiceRepository.findByCaregiverId(caregiverId);
    }

    public List<Invoice> getInvoicesByStatut(Invoice.StatutFacture statut) {
        return invoiceRepository.findByStatut(statut);
    }

    public List<Invoice> getInvoicesByPeriode(LocalDate debut, LocalDate fin) {
        return invoiceRepository.findByDateFactureBetween(debut, fin);
    }

    public BillingStats getStats() {
        BillingStats stats = new BillingStats();
        stats.setTotalFactures(invoiceRepository.count());
        stats.setRevenuTotal(invoiceRepository.getTotalRevenuPaye());
        stats.setMontantEnAttente(invoiceRepository.getTotalEnAttente());
        stats.setFacturesPayees(invoiceRepository.findByStatut(Invoice.StatutFacture.PAYEE).size());
        stats.setFacturesEnAttente(invoiceRepository.findByStatut(Invoice.StatutFacture.EN_ATTENTE).size());
        return stats;
    }
}
