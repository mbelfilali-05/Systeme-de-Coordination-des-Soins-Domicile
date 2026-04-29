#!/bin/bash
# ============================================================
# Script de démarrage — Soins à Domicile Microservices
# Lance tous les services dans l'ordre correct
# ============================================================

echo "=============================================="
echo " Système de Coordination des Soins à Domicile"
echo " Démarrage de tous les microservices..."
echo "=============================================="

PROJECT_DIR=$(dirname "$(readlink -f "$0")")

# Couleurs
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m'

echo ""
echo -e "${YELLOW}[1/7] Démarrage Eureka Server (port 8761)...${NC}"
cd "$PROJECT_DIR/eureka-server" && mvn spring-boot:run -q &
sleep 15

echo -e "${YELLOW}[2/7] Démarrage API Gateway (port 8080)...${NC}"
cd "$PROJECT_DIR/gateway-service" && mvn spring-boot:run -q &
sleep 5

echo -e "${YELLOW}[3/7] Démarrage Patient Service (port 8081)...${NC}"
cd "$PROJECT_DIR/patient-home-service" && mvn spring-boot:run -q &
sleep 3

echo -e "${YELLOW}[4/7] Démarrage Caregiver Service (port 8082)...${NC}"
cd "$PROJECT_DIR/caregiver-scheduling-service" && mvn spring-boot:run -q &
sleep 3

echo -e "${YELLOW}[5/7] Démarrage Visit Optimization Service (port 8083)...${NC}"
cd "$PROJECT_DIR/visit-optimization-service" && mvn spring-boot:run -q &
sleep 3

echo -e "${YELLOW}[6/7] Démarrage Tracking Service (port 8084)...${NC}"
cd "$PROJECT_DIR/real-time-tracking-service" && mvn spring-boot:run -q &
sleep 3

echo -e "${YELLOW}[7/7] Démarrage Billing Service (port 8085)...${NC}"
cd "$PROJECT_DIR/billing-reconciliation-service" && mvn spring-boot:run -q &

echo ""
echo -e "${GREEN}=============================================="
echo " Tous les services sont en cours de démarrage!"
echo ""
echo " Eureka Dashboard : http://localhost:8761"
echo " API Gateway      : http://localhost:8080"
echo ""
echo " Patients         : http://localhost:8080/api/patients"
echo " Soignants        : http://localhost:8080/api/caregivers"
echo " Visites          : http://localhost:8080/api/visits"
echo " Tracking         : http://localhost:8080/api/tracking"
echo " Facturation      : http://localhost:8080/api/billing"
echo "=============================================="
echo -e "${NC}"

wait
