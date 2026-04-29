# Système de Coordination des Soins à Domicile

## Projet 6 — Architecture Microservices
**Filière INDIA 2ème année — Semestre 4**  
**Virtualisation & Architectures Logicielles Distribuées**  
**ENSAM — Université Mohammed V de Rabat — A.U. 2025-2026**

---

## Description

Ce projet implémente un **système de coordination des soins à domicile** basé sur une architecture **microservices** avec Spring Boot et Spring Cloud. Il optimise la planification des visites à domicile des soignants en tenant compte des contraintes géographiques, temporelles et cliniques.

## Architecture

```
                    ┌──────────────────┐
                    │   Eureka Server  │
                    │    (port 8761)   │
                    └────────┬─────────┘
                             │ Service Discovery
                    ┌────────┴─────────┐
                    │   API Gateway    │
                    │   (port 8080)    │
                    └────────┬─────────┘
                             │
          ┌──────────┬───────┼────────┬──────────┐
          │          │       │        │          │
   ┌──────┴──┐ ┌────┴───┐ ┌─┴──────┐ ┌┴────────┐ ┌┴────────┐
   │ Patient │ │Caregiver│ │ Visit  │ │Tracking │ │ Billing │
   │ Service │ │Schedul. │ │Optimiz.│ │ Service │ │ Service │
   │  :8081  │ │  :8082  │ │ :8083  │ │  :8084  │ │  :8085  │
   └─────────┘ └─────────┘ └────────┘ └─────────┘ └─────────┘
       H2 DB      H2 DB      H2 DB      H2 DB       H2 DB
```

## Microservices

| Service | Port | Rôle |
|---------|------|------|
| `eureka-server` | 8761 | Service de découverte (Netflix Eureka) |
| `gateway-service` | 8080 | Passerelle API (Spring Cloud Gateway) |
| `patient-home-service` | 8081 | Profil patient à domicile, besoins, contraintes |
| `caregiver-scheduling-service` | 8082 | Planning des soignants, disponibilités, compétences |
| `visit-optimization-service` | 8083 | Algorithme d'optimisation de tournées (géolocalisation) |
| `real-time-tracking-service` | 8084 | Suivi en temps réel des visites, retards, incidents |
| `billing-reconciliation-service` | 8085 | Facturation, prise en charge, rapprochement |

## Technologies utilisées

- **Java 21** + **Spring Boot 3.2.5**
- **Spring Cloud 2023.0.1** (Eureka, Gateway)
- **Spring Data JPA** + **H2 Database** (en mémoire)
- **API REST** (JSON)
- **Docker** + **Docker Compose**
- **Maven** (multi-module)

## Prérequis

- **Java 17+** (recommandé: Java 21)
- **Maven 3.8+**
- **Docker** et **Docker Compose** (optionnel, pour le déploiement conteneurisé)

## Démarrage rapide (sans Docker)

### 1. Cloner / décompresser le projet

```bash
cd soins-domicile-microservices
```

### 2. Compiler tous les modules

```bash
mvn clean install -DskipTests
```

### 3. Lancer les services (dans cet ordre, chacun dans un terminal séparé)

```bash
# Terminal 1 — Eureka Server (attendre qu'il démarre complètement)
cd eureka-server
mvn spring-boot:run

# Terminal 2 — API Gateway
cd gateway-service
mvn spring-boot:run

# Terminal 3 — Patient Service
cd patient-home-service
mvn spring-boot:run

# Terminal 4 — Caregiver Service
cd caregiver-scheduling-service
mvn spring-boot:run

# Terminal 5 — Visit Optimization Service
cd visit-optimization-service
mvn spring-boot:run

# Terminal 6 — Real-Time Tracking Service
cd real-time-tracking-service
mvn spring-boot:run

# Terminal 7 — Billing Service
cd billing-reconciliation-service
mvn spring-boot:run
```

### 4. Vérifier le bon fonctionnement

- **Eureka Dashboard** : http://localhost:8761
- **H2 Console (Patient)** : http://localhost:8081/h2-console (JDBC URL: `jdbc:h2:mem:patientdb`)

## Démarrage avec Docker Compose

```bash
docker compose up --build
```

## Tester les API

### Patients (via Gateway)
```bash
# Lister tous les patients
curl http://localhost:8080/api/patients

# Obtenir un patient par ID
curl http://localhost:8080/api/patients/1

# Créer un patient
curl -X POST http://localhost:8080/api/patients \
  -H "Content-Type: application/json" \
  -d '{"nom":"Tazi","prenom":"Omar","adresse":"10 Rue Atlas, Rabat","latitude":34.015,"longitude":-6.835,"besoins":"Pansements","niveauUrgence":"NORMAL"}'
```

### Soignants
```bash
# Lister les soignants disponibles
curl http://localhost:8080/api/caregivers/disponibles

# Soignants disponibles par spécialité
curl http://localhost:8080/api/caregivers/disponibles/INFIRMIER
```

### Optimisation de tournée (endpoint clé du projet)
```bash
curl -X POST http://localhost:8080/api/visits/optimize \
  -H "Content-Type: application/json" \
  -d '{
    "caregiverId": 1,
    "caregiverNom": "Mansouri Rachid",
    "caregiverLatitude": 34.0105,
    "caregiverLongitude": -6.8370,
    "date": "2025-06-15",
    "patients": [
      {"patientId":1,"patientNom":"Alaoui Fatima","latitude":34.0209,"longitude":-6.8416,"typeSoin":"Injection","dureeSoinMinutes":20},
      {"patientId":3,"patientNom":"Chraibi Amina","latitude":33.9923,"longitude":-6.8498,"typeSoin":"Soins palliatifs","dureeSoinMinutes":45},
      {"patientId":4,"patientNom":"Drissi Karim","latitude":33.9575,"longitude":-6.8693,"typeSoin":"Pansement","dureeSoinMinutes":25},
      {"patientId":5,"patientNom":"El Fassi Nadia","latitude":33.9267,"longitude":-6.9073,"typeSoin":"Perfusion","dureeSoinMinutes":40}
    ]
  }'
```

### Tracking
```bash
# Enregistrer un événement
curl -X POST http://localhost:8080/api/tracking \
  -H "Content-Type: application/json" \
  -d '{"visitId":1,"caregiverId":1,"patientId":1,"eventType":"ARRIVEE","latitude":34.0209,"longitude":-6.8416,"details":"Arrivée chez patient Alaoui"}'
```

### Facturation
```bash
# Statistiques de facturation
curl http://localhost:8080/api/billing/stats

# Factures par statut
curl http://localhost:8080/api/billing/statut/EN_ATTENTE
```

## Algorithme d'optimisation

Le service `visit-optimization-service` implémente l'**algorithme du plus proche voisin (Nearest Neighbor)** pour optimiser les tournées :

1. **Point de départ** : position de base du soignant
2. **Calcul de distance** : formule de Haversine (distance géodésique entre coordonnées GPS)
3. **Sélection** : à chaque étape, le patient non visité le plus proche est choisi
4. **Résultat** : ordre optimal de passage, distance totale, heures estimées d'arrivée

## Données de démonstration

Au démarrage, chaque service charge automatiquement des données de test réalistes basées sur la région de **Rabat-Salé-Témara** :
- 5 patients avec coordonnées GPS réelles
- 4 soignants avec spécialités différentes
- 3 factures avec différents statuts

## Structure du projet

```
soins-domicile-microservices/
├── pom.xml                          # POM parent (multi-module Maven)
├── docker-compose.yml               # Orchestration Docker
├── README.md
├── eureka-server/                   # Service de découverte
├── gateway-service/                 # Passerelle API
├── patient-home-service/            # Gestion des patients
├── caregiver-scheduling-service/    # Planning des soignants
├── visit-optimization-service/      # Optimisation des tournées
├── real-time-tracking-service/      # Suivi temps réel
└── billing-reconciliation-service/  # Facturation
```

## Acteurs du système

- **Patients à domicile / Aidants** : bénéficiaires des soins
- **Infirmiers libéraux / Aides-soignants** : soignants effectuant les visites
- **Coordinateurs de soins / Planificateurs** : utilisateurs de l'interface de planification
- **Médecins prescripteurs** : prescrivent les soins à réaliser

## Auteurs

Projet réalisé dans le cadre du module Virtualisation & Architectures Logicielles Distribuées  
Pr. Abderrahim EL QADI — ENSAM Rabat
