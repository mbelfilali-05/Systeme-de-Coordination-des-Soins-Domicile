package ma.ensam.soins.visit.service;

import ma.ensam.soins.visit.dto.OptimizationRequest;
import ma.ensam.soins.visit.dto.OptimizationResult;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Algorithme d'optimisation des tournées de soignants.
 * Utilise l'heuristique du plus proche voisin (Nearest Neighbor)
 * pour minimiser la distance totale parcourue.
 *
 * Principe :
 * 1. Partir de la position de base du soignant
 * 2. À chaque étape, choisir le patient non visité le plus proche
 * 3. Répéter jusqu'à avoir visité tous les patients
 */
@Service
public class RouteOptimizationService {

    private static final double VITESSE_MOYENNE_KMH = 30.0; // Vitesse moyenne en ville
    private static final int DUREE_SOIN_DEFAULT_MINUTES = 30;

    /**
     * Calcule la distance en km entre deux points GPS (formule de Haversine)
     */
    public double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371.0; // Rayon de la Terre en km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    /**
     * Optimise la tournée d'un soignant en utilisant l'algorithme du plus proche voisin.
     */
    public OptimizationResult optimiserTournee(OptimizationRequest request) {
        List<OptimizationRequest.PatientPoint> patients = new ArrayList<>(request.getPatients());
        List<OptimizationResult.VisitStep> tournee = new ArrayList<>();

        double currentLat = request.getCaregiverLatitude();
        double currentLon = request.getCaregiverLongitude();
        double distanceTotale = 0.0;
        int dureeEstimeeTotale = 0;
        LocalTime heureActuelle = LocalTime.of(8, 0); // Début de journée à 8h

        List<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < patients.size(); i++) {
            visited.add(false);
        }

        int ordre = 1;

        // Algorithme du plus proche voisin (Nearest Neighbor)
        while (tournee.size() < patients.size()) {
            int nearestIndex = -1;
            double nearestDistance = Double.MAX_VALUE;

            // Trouver le patient non visité le plus proche
            for (int i = 0; i < patients.size(); i++) {
                if (!visited.get(i)) {
                    OptimizationRequest.PatientPoint p = patients.get(i);
                    double dist = haversineDistance(currentLat, currentLon, p.getLatitude(), p.getLongitude());
                    if (dist < nearestDistance) {
                        nearestDistance = dist;
                        nearestIndex = i;
                    }
                }
            }

            if (nearestIndex == -1) break;

            visited.set(nearestIndex, true);
            OptimizationRequest.PatientPoint patient = patients.get(nearestIndex);

            // Calculer le temps de trajet
            int tempsTrajetMinutes = (int) Math.ceil((nearestDistance / VITESSE_MOYENNE_KMH) * 60);
            heureActuelle = heureActuelle.plusMinutes(tempsTrajetMinutes);

            // Créer l'étape de la tournée
            OptimizationResult.VisitStep step = new OptimizationResult.VisitStep();
            step.setOrdre(ordre);
            step.setPatientId(patient.getPatientId());
            step.setPatientNom(patient.getPatientNom());
            step.setLatitude(patient.getLatitude());
            step.setLongitude(patient.getLongitude());
            step.setTypeSoin(patient.getTypeSoin());
            step.setDistanceDepuisPrecedentKm(Math.round(nearestDistance * 100.0) / 100.0);
            step.setHeureEstimeeArrivee(heureActuelle.format(DateTimeFormatter.ofPattern("HH:mm")));

            tournee.add(step);

            // Mettre à jour la position courante et les totaux
            currentLat = patient.getLatitude();
            currentLon = patient.getLongitude();
            distanceTotale += nearestDistance;

            int dureeSoin = patient.getDureeSoinMinutes() > 0 ? patient.getDureeSoinMinutes() : DUREE_SOIN_DEFAULT_MINUTES;
            heureActuelle = heureActuelle.plusMinutes(dureeSoin);
            dureeEstimeeTotale += tempsTrajetMinutes + dureeSoin;

            ordre++;
        }

        // Construire le résultat
        OptimizationResult result = new OptimizationResult();
        result.setCaregiverId(request.getCaregiverId());
        result.setCaregiverNom(request.getCaregiverNom());
        result.setTournee(tournee);
        result.setDistanceTotaleKm(Math.round(distanceTotale * 100.0) / 100.0);
        result.setDureeEstimeeTotaleMinutes(dureeEstimeeTotale);
        result.setNombreVisites(tournee.size());

        return result;
    }
}
