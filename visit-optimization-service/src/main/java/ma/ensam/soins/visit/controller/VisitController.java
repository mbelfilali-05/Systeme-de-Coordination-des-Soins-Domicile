package ma.ensam.soins.visit.controller;

import ma.ensam.soins.visit.dto.OptimizationRequest;
import ma.ensam.soins.visit.dto.OptimizationResult;
import ma.ensam.soins.visit.model.Visit;
import ma.ensam.soins.visit.service.RouteOptimizationService;
import ma.ensam.soins.visit.service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/visits")
@CrossOrigin(origins = "*")
public class VisitController {

    private final VisitService visitService;
    private final RouteOptimizationService optimizationService;

    public VisitController(VisitService visitService, RouteOptimizationService optimizationService) {
        this.visitService = visitService;
        this.optimizationService = optimizationService;
    }

    @GetMapping
    public List<Visit> getAllVisits() {
        return visitService.getAllVisits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visit> getVisitById(@PathVariable Long id) {
        return visitService.getVisitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Visit> createVisit(@RequestBody Visit visit) {
        return ResponseEntity.status(HttpStatus.CREATED).body(visitService.createVisit(visit));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visit> updateVisit(@PathVariable Long id, @RequestBody Visit visit) {
        return ResponseEntity.ok(visitService.updateVisit(id, visit));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisit(@PathVariable Long id) {
        visitService.deleteVisit(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/caregiver/{caregiverId}")
    public List<Visit> getVisitsByCaregiver(@PathVariable Long caregiverId,
                                            @RequestParam(required = false) String date) {
        LocalDate d = (date != null) ? LocalDate.parse(date) : LocalDate.now();
        return visitService.getVisitsByCaregiver(caregiverId, d);
    }

    @GetMapping("/date/{date}")
    public List<Visit> getVisitsByDate(@PathVariable String date) {
        return visitService.getVisitsByDate(LocalDate.parse(date));
    }

    @GetMapping("/patient/{patientId}")
    public List<Visit> getVisitsByPatient(@PathVariable Long patientId) {
        return visitService.getVisitsByPatient(patientId);
    }

    /**
     * Endpoint principal d'optimisation de tournée.
     * Reçoit une liste de patients à visiter et retourne l'ordre optimal.
     */
    @PostMapping("/optimize")
    public ResponseEntity<OptimizationResult> optimizeTournee(@RequestBody OptimizationRequest request) {
        OptimizationResult result = optimizationService.optimiserTournee(request);
        return ResponseEntity.ok(result);
    }
}
