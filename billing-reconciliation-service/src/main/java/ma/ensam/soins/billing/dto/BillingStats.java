package ma.ensam.soins.billing.dto;

public class BillingStats {
    private long totalFactures;
    private Double revenuTotal;
    private Double montantEnAttente;
    private long facturesPayees;
    private long facturesEnAttente;

    public long getTotalFactures() { return totalFactures; }
    public void setTotalFactures(long totalFactures) { this.totalFactures = totalFactures; }
    public Double getRevenuTotal() { return revenuTotal; }
    public void setRevenuTotal(Double revenuTotal) { this.revenuTotal = revenuTotal; }
    public Double getMontantEnAttente() { return montantEnAttente; }
    public void setMontantEnAttente(Double montantEnAttente) { this.montantEnAttente = montantEnAttente; }
    public long getFacturesPayees() { return facturesPayees; }
    public void setFacturesPayees(long facturesPayees) { this.facturesPayees = facturesPayees; }
    public long getFacturesEnAttente() { return facturesEnAttente; }
    public void setFacturesEnAttente(long facturesEnAttente) { this.facturesEnAttente = facturesEnAttente; }
}
