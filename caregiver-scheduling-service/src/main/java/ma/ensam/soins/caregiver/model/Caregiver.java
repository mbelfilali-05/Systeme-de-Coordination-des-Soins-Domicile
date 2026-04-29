package ma.ensam.soins.caregiver.model;

import jakarta.persistence.*;

@Entity
@Table(name = "caregivers")
public class Caregiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    private String telephone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Specialite specialite;

    private Double latitudeBase; // Position de départ (domicile du soignant)
    private Double longitudeBase;

    private boolean disponible = true;

    @Column(length = 500)
    private String competences; // Compétences détaillées

    private String zoneCouverture; // Zone géographique couverte

    private int capaciteJournaliere = 8; // Nombre max de visites par jour

    public enum Specialite {
        INFIRMIER, AIDE_SOIGNANT, KINESITHERAPEUTE, MEDECIN, PSYCHOLOGUE
    }

    public Caregiver() {}

    public Caregiver(String nom, String prenom, Specialite specialite, Double latitudeBase, Double longitudeBase) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.latitudeBase = latitudeBase;
        this.longitudeBase = longitudeBase;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public Specialite getSpecialite() { return specialite; }
    public void setSpecialite(Specialite specialite) { this.specialite = specialite; }
    public Double getLatitudeBase() { return latitudeBase; }
    public void setLatitudeBase(Double latitudeBase) { this.latitudeBase = latitudeBase; }
    public Double getLongitudeBase() { return longitudeBase; }
    public void setLongitudeBase(Double longitudeBase) { this.longitudeBase = longitudeBase; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public String getCompetences() { return competences; }
    public void setCompetences(String competences) { this.competences = competences; }
    public String getZoneCouverture() { return zoneCouverture; }
    public void setZoneCouverture(String zoneCouverture) { this.zoneCouverture = zoneCouverture; }
    public int getCapaciteJournaliere() { return capaciteJournaliere; }
    public void setCapaciteJournaliere(int capaciteJournaliere) { this.capaciteJournaliere = capaciteJournaliere; }
}
