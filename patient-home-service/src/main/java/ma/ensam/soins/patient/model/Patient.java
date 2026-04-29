package ma.ensam.soins.patient.model;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    private String telephone;

    @Column(nullable = false)
    private String adresse;

    private Double latitude;
    private Double longitude;

    @Column(length = 1000)
    private String besoins; // Description des besoins médicaux

    @Column(length = 500)
    private String contraintes; // Contraintes spécifiques (horaires, accessibilité)

    @Enumerated(EnumType.STRING)
    private NiveauUrgence niveauUrgence = NiveauUrgence.NORMAL;

    public enum NiveauUrgence {
        FAIBLE, NORMAL, URGENT, CRITIQUE
    }

    public Patient() {}

    public Patient(String nom, String prenom, String adresse, Double latitude, Double longitude) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getBesoins() { return besoins; }
    public void setBesoins(String besoins) { this.besoins = besoins; }

    public String getContraintes() { return contraintes; }
    public void setContraintes(String contraintes) { this.contraintes = contraintes; }

    public NiveauUrgence getNiveauUrgence() { return niveauUrgence; }
    public void setNiveauUrgence(NiveauUrgence niveauUrgence) { this.niveauUrgence = niveauUrgence; }
}
