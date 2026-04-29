package ma.ensam.soins.visit.dto;

import java.util.List;

public class OptimizationRequest {

    private Long caregiverId;
    private String caregiverNom;
    private Double caregiverLatitude;
    private Double caregiverLongitude;
    private List<PatientPoint> patients;
    private String date; // format: yyyy-MM-dd

    public static class PatientPoint {
        private Long patientId;
        private String patientNom;
        private Double latitude;
        private Double longitude;
        private String typeSoin;
        private int dureeSoinMinutes;
        private String fenetre; // ex: "08:00-12:00"

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
        public int getDureeSoinMinutes() { return dureeSoinMinutes; }
        public void setDureeSoinMinutes(int dureeSoinMinutes) { this.dureeSoinMinutes = dureeSoinMinutes; }
        public String getFenetre() { return fenetre; }
        public void setFenetre(String fenetre) { this.fenetre = fenetre; }
    }

    // Getters & Setters
    public Long getCaregiverId() { return caregiverId; }
    public void setCaregiverId(Long caregiverId) { this.caregiverId = caregiverId; }
    public String getCaregiverNom() { return caregiverNom; }
    public void setCaregiverNom(String caregiverNom) { this.caregiverNom = caregiverNom; }
    public Double getCaregiverLatitude() { return caregiverLatitude; }
    public void setCaregiverLatitude(Double caregiverLatitude) { this.caregiverLatitude = caregiverLatitude; }
    public Double getCaregiverLongitude() { return caregiverLongitude; }
    public void setCaregiverLongitude(Double caregiverLongitude) { this.caregiverLongitude = caregiverLongitude; }
    public List<PatientPoint> getPatients() { return patients; }
    public void setPatients(List<PatientPoint> patients) { this.patients = patients; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
