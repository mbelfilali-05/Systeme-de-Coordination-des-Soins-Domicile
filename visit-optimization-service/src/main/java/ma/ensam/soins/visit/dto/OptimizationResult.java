package ma.ensam.soins.visit.dto;

import java.util.List;

public class OptimizationResult {

    private Long caregiverId;
    private String caregiverNom;
    private List<VisitStep> tournee;
    private double distanceTotaleKm;
    private int dureeEstimeeTotaleMinutes;
    private int nombreVisites;

    public static class VisitStep {
        private int ordre;
        private Long patientId;
        private String patientNom;
        private Double latitude;
        private Double longitude;
        private String typeSoin;
        private String heureEstimeeArrivee;
        private double distanceDepuisPrecedentKm;

        public int getOrdre() { return ordre; }
        public void setOrdre(int ordre) { this.ordre = ordre; }
        public Long getPatientId() { return patientId; }
        public void setPatientId(Long patientId) { this.patientId = patientId; }
        public String getPatientNom() { return patientNom; }
        public void setPatientNom(String patientNom) { this.patientNom = patientNom; }
        public Double getLatitude() { return latitude; }
        public void setLatitude(Double latitude) { this.latitude = latitude; }
        public Double getLongitude() { return longitude; }
        public void setLongitude(Double longitude) { this.longitude = longitude; }
        public String getTypeSoin() { return typeSoin; }
        public void setTypeSoin(String typeSoin) { this.typeSoin = typeSoin; }
        public String getHeureEstimeeArrivee() { return heureEstimeeArrivee; }
        public void setHeureEstimeeArrivee(String heureEstimeeArrivee) { this.heureEstimeeArrivee = heureEstimeeArrivee; }
        public double getDistanceDepuisPrecedentKm() { return distanceDepuisPrecedentKm; }
        public void setDistanceDepuisPrecedentKm(double distanceDepuisPrecedentKm) { this.distanceDepuisPrecedentKm = distanceDepuisPrecedentKm; }
    }

    // Getters & Setters
    public Long getCaregiverId() { return caregiverId; }
    public void setCaregiverId(Long caregiverId) { this.caregiverId = caregiverId; }
    public String getCaregiverNom() { return caregiverNom; }
    public void setCaregiverNom(String caregiverNom) { this.caregiverNom = caregiverNom; }
    public List<VisitStep> getTournee() { return tournee; }
    public void setTournee(List<VisitStep> tournee) { this.tournee = tournee; }
    public double getDistanceTotaleKm() { return distanceTotaleKm; }
    public void setDistanceTotaleKm(double distanceTotaleKm) { this.distanceTotaleKm = distanceTotaleKm; }
    public int getDureeEstimeeTotaleMinutes() { return dureeEstimeeTotaleMinutes; }
    public void setDureeEstimeeTotaleMinutes(int dureeEstimeeTotaleMinutes) { this.dureeEstimeeTotaleMinutes = dureeEstimeeTotaleMinutes; }
    public int getNombreVisites() { return nombreVisites; }
    public void setNombreVisites(int nombreVisites) { this.nombreVisites = nombreVisites; }
}
