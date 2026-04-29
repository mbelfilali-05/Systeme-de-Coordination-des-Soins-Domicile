package ma.ensam.soins.caregiver.controller;

import ma.ensam.soins.caregiver.model.Caregiver;
import ma.ensam.soins.caregiver.service.CaregiverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/caregivers")
@CrossOrigin(origins = "*")
public class CaregiverController {

    private final CaregiverService caregiverService;

    public CaregiverController(CaregiverService caregiverService) {
        this.caregiverService = caregiverService;
    }

    @GetMapping
    public List<Caregiver> getAllCaregivers() {
        return caregiverService.getAllCaregivers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caregiver> getCaregiverById(@PathVariable Long id) {
        return caregiverService.getCaregiverById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Caregiver> createCaregiver(@RequestBody Caregiver caregiver) {
        return ResponseEntity.status(HttpStatus.CREATED).body(caregiverService.createCaregiver(caregiver));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Caregiver> updateCaregiver(@PathVariable Long id, @RequestBody Caregiver caregiver) {
        return ResponseEntity.ok(caregiverService.updateCaregiver(id, caregiver));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaregiver(@PathVariable Long id) {
        caregiverService.deleteCaregiver(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponibles")
    public List<Caregiver> getAvailableCaregivers() {
        return caregiverService.getAvailableCaregivers();
    }

    @GetMapping("/disponibles/{specialite}")
    public List<Caregiver> getAvailableBySpecialite(@PathVariable Caregiver.Specialite specialite) {
        return caregiverService.getAvailableBySpecialite(specialite);
    }
}
