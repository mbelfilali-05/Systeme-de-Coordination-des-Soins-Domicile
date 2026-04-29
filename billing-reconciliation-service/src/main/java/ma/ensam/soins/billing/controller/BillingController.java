package ma.ensam.soins.billing.controller;

import ma.ensam.soins.billing.dto.BillingStats;
import ma.ensam.soins.billing.model.Invoice;
import ma.ensam.soins.billing.service.BillingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/billing")
@CrossOrigin(origins = "*")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return billingService.getAllInvoices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        return billingService.getInvoiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<Invoice> getByNumero(@PathVariable String numero) {
        return billingService.getInvoiceByNumero(numero)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        return ResponseEntity.status(HttpStatus.CREATED).body(billingService.createInvoice(invoice));
    }

    @PatchMapping("/{id}/statut")
    public ResponseEntity<Invoice> updateStatut(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Invoice.StatutFacture statut = Invoice.StatutFacture.valueOf(body.get("statut"));
        return ResponseEntity.ok(billingService.updateStatut(id, statut));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        billingService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/patient/{patientId}")
    public List<Invoice> getByPatient(@PathVariable Long patientId) {
        return billingService.getInvoicesByPatient(patientId);
    }

    @GetMapping("/caregiver/{caregiverId}")
    public List<Invoice> getByCaregiver(@PathVariable Long caregiverId) {
        return billingService.getInvoicesByCaregiver(caregiverId);
    }

    @GetMapping("/statut/{statut}")
    public List<Invoice> getByStatut(@PathVariable Invoice.StatutFacture statut) {
        return billingService.getInvoicesByStatut(statut);
    }

    @GetMapping("/periode")
    public List<Invoice> getByPeriode(@RequestParam String debut, @RequestParam String fin) {
        return billingService.getInvoicesByPeriode(LocalDate.parse(debut), LocalDate.parse(fin));
    }

    @GetMapping("/stats")
    public BillingStats getStats() {
        return billingService.getStats();
    }
}
