package ma.ensam.soins.visit.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;
    private Long caregiverId;

    private String patientNom;
    private String caregiverNom;

    private LocalDate dateVisite;
    private LocalTime heureDebut;
    private LocalTime heureFin;

    private Double patientLatitude;
    private Double patientLongitude;

    @Enumerated(EnumType.STRING)
    private StatutVisite statut = StatutVisite.PLANIFIEE;

    private String typeSoin;
    private String notes;
    private Integer ordrePassage; // Ordre dans la tournée du soignant
    private Double distanceDepuisPrecedent; // en km

    public enum StatutVisite {
        PLANIFIEE, EN_COURS, TERMINEE, ANNULEE, RETARDEE
    }

    public Visit() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public Long getCaregiverId() { return caregiverId; }
    public void setCaregiverId(Long caregiverId) { this.caregiverId = caregiverId; }
    public String getPatientNom() { return patientNom; }
    public void setPatientNom(String patientNom) { this.patientNom = patientNom; }
    public String getCaregiverNom() { return caregiverNom; }
    public void setCaregiverNom(String caregiverNom) { this.caregiverNom = caregiverNom; }
    public LocalDate getDateVisite() { return dateVisite; }
    public void setDateVisite(LocalDate dateVisite) { this.dateVisite = dateVisite; }
    public LocalTime getHeureDebut() { return heureDebut; }
    public void setHeureDebut(LocalTime heureDebut) { this.heureDebut = heureDebut; }
    public LocalTime getHeureFin() { return heureFin; }
    public void setHeureFin(LocalTime heureFin) { this.heureFin = heureFin; }
    public Double getPatientLatitude() { return patientLatitude; }
    public void setPatientLatitude(Double patientLatitude) { this.patientLatitude = patientLatitude; }
    public Double getPatientLongitude() { return patientLongitude; }
    public void setPatientLongitude(Double patientLongitude) { this.patientLongitude = patientLongitude; }
    public StatutVisite getStatut() { return statut; }
    public void setStatut(StatutVisite statut) { this.statut = statut; }
    public String getTypeSoin() { return typeSoin; }
    public void setTypeSoin(String typeSoin) { this.typeSoin = typeSoin; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Integer getOrdrePassage() { return ordrePassage; }
    public void setOrdrePassage(Integer ordrePassage) { this.ordrePassage = ordrePassage; }
    public Double getDistanceDepuisPrecedent() { return distanceDepuisPrecedent; }
    public void setDistanceDepuisPrecedent(Double distanceDepuisPrecedent) { this.distanceDepuisPrecedent = distanceDepuisPrecedent; }
}
