@echo off
REM ============================================================
REM Script de demarrage — Soins a Domicile Microservices
REM Lance tous les services dans l'ordre correct (Windows)
REM ============================================================

echo ==============================================
echo  Systeme de Coordination des Soins a Domicile
echo  Demarrage de tous les microservices...
echo ==============================================

echo.
echo [1/7] Demarrage Eureka Server (port 8761)...
start "Eureka Server" cmd /c "cd eureka-server && mvn spring-boot:run"
timeout /t 20 /nobreak > nul

echo [2/7] Demarrage API Gateway (port 8080)...
start "API Gateway" cmd /c "cd gateway-service && mvn spring-boot:run"
timeout /t 8 /nobreak > nul

echo [3/7] Demarrage Patient Service (port 8081)...
start "Patient Service" cmd /c "cd patient-home-service && mvn spring-boot:run"
timeout /t 5 /nobreak > nul

echo [4/7] Demarrage Caregiver Service (port 8082)...
start "Caregiver Service" cmd /c "cd caregiver-scheduling-service && mvn spring-boot:run"
timeout /t 5 /nobreak > nul

echo [5/7] Demarrage Visit Optimization (port 8083)...
start "Visit Service" cmd /c "cd visit-optimization-service && mvn spring-boot:run"
timeout /t 5 /nobreak > nul

echo [6/7] Demarrage Tracking Service (port 8084)...
start "Tracking Service" cmd /c "cd real-time-tracking-service && mvn spring-boot:run"
timeout /t 5 /nobreak > nul

echo [7/7] Demarrage Billing Service (port 8085)...
start "Billing Service" cmd /c "cd billing-reconciliation-service && mvn spring-boot:run"

echo.
echo ==============================================
echo  Tous les services demarrent!
echo.
echo  Eureka Dashboard : http://localhost:8761
echo  API Gateway      : http://localhost:8080
echo.
echo  Patients         : http://localhost:8080/api/patients
echo  Soignants        : http://localhost:8080/api/caregivers
echo  Visites          : http://localhost:8080/api/visits
echo  Tracking         : http://localhost:8080/api/tracking
echo  Facturation      : http://localhost:8080/api/billing
echo ==============================================
pause
