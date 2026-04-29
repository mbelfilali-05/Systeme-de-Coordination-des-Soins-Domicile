package ma.ensam.soins.billing.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numeroFacture;

    private Long patientId;
    private String patientNom;

    private Long caregiverId;
    private String caregiverNom;

    private Long visitId;

    private LocalDate dateFacture;
    private LocalDate dateSoin;

    private String typeSoin;
    private Double montantHT;
    private Double tauxTVA = 0.0; // Soins médicaux souvent exonérés
    private Double montantTTC;

    @Enumerated(EnumType.STRING)
    private StatutFacture statut = StatutFacture.EN_ATTENTE;

    @Enumerated(EnumType.STRING)
    private ModePaiement modePaiement;

    private String referenceAssurance;
    private Double partAssurance;  // Montant pris en charge par l'assurance
    private Double partPatient;    // Reste à charge du patient

    private LocalDateTime createdAt;

    public enum StatutFacture {
        EN_ATTENTE, VALIDEE, ENVOYEE, PAYEE, PARTIELLEMENT_PAYEE, ANNULEE, EN_LITIGE
    }

    public enum ModePaiement {
        ESPECES, CHEQUE, VIREMENT, CARTE, ASSURANCE, MIXTE
    }

    public Invoice() {
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        if (this.dateFacture == null) this.dateFacture = LocalDate.now();
        if (this.createdAt == null) this.createdAt = LocalDateTime.now();
        if (this.montantHT != null) {
            this.montantTTC = this.montantHT * (1 + this.tauxTVA);
        }
        if (this.numeroFacture == null) {
            this.numeroFacture = "FACT-" + System.currentTimeMillis();
        }
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumeroFacture() { return numeroFacture; }
    public void setNumeroFacture(String numeroFacture) { this.numeroFacture = numeroFacture; }
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public String getPatientNom() { return patientNom; }
    public void setPatientNom(String patientNom) { this.patientNom = patientNom; }
    public Long getCaregiverId() { return caregiverId; }
    public void setCaregiverId(Long caregiverId) { this.caregiverId = caregiverId; }
    public String getCaregiverNom() { return caregiverNom; }
    public void setCaregiverNom(String caregiverNom) { this.caregiverNom = caregiverNom; }
    public Long getVisitId() { return visitId; }
    public void setVisitId(Long visitId) { this.visitId = visitId; }
    public LocalDate getDateFacture() { return dateFacture; }
    public void setDateFacture(LocalDate dateFacture) { this.dateFacture = dateFacture; }
    public LocalDate getDateSoin() { return dateSoin; }
    public void setDateSoin(LocalDate dateSoin) { this.dateSoin = dateSoin; }
    public String getTypeSoin() { return typeSoin; }
    public void setTypeSoin(String typeSoin) { this.typeSoin = typeSoin; }
    public Double getMontantHT() { return montantHT; }
    public void setMontantHT(Double montantHT) { this.montantHT = montantHT; }
    public Double getTauxTVA() { return tauxTVA; }
    public void setTauxTVA(Double tauxTVA) { this.tauxTVA = tauxTVA; }
    public Double getMontantTTC() { return montantTTC; }
    public void setMontantTTC(Double montantTTC) { this.montantTTC = montantTTC; }
    public StatutFacture getStatut() { return statut; }
    public void setStatut(StatutFacture statut) { this.statut = statut; }
    public ModePaiement getModePaiement() { return modePaiement; }
    public void setModePaiement(ModePaiement modePaiement) { this.modePaiement = modePaiement; }
    public String getReferenceAssurance() { return referenceAssurance; }
    public void setReferenceAssurance(String referenceAssurance) { this.referenceAssurance = referenceAssurance; }
    public Double getPartAssurance() { return partAssurance; }
    public void setPartAssurance(Double partAssurance) { this.partAssurance = partAssurance; }
    public Double getPartPatient() { return partPatient; }
    public void setPartPatient(Double partPatient) { this.partPatient = partPatient; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
