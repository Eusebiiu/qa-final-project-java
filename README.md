

Acest proiect demonstrează un pipeline complet de QA Automation, de la setup-ul local la un pipeline CI/CD funcțional cu Docker pe GitHub Actions.

## 1. Status CI/CD (Badge de Status)
[Exemplu Badge](https://github.com/Eusebiiu/qa-final-project-java/actions/workflows/ci.yml/badge.svg)

## 2. Ce face proiectul
Proiectul conține structura de foldere a unui proiect Java/Maven de QA. Include un fișier de configurare YAML și un test API scris în pseudocod, simulând un plan de testare real. În plus, proiectul este containerizat cu Docker și automatizat printr-un pipeline GitHub Actions.

## 3. Cum se rulează testele (Simulat)
Pentru a simula rularea testelor local, folosește comanda Maven:
```bash
mvn test