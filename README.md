# NutriTrack Backend

REST API für die NutriTrack Webanwendung zur Kalorienverfolgung.

## Tech-Stack

- **Framework:** Spring Boot
- **Sprache:** Java 21
- **Datenbank:** PostgreSQL
- **Deployment:** Render.com
- **CI/CD:** GitHub Actions

## API Endpunkte

| Method | URL | Beschreibung |
|---|---|---|
| GET | /meals | Alle Mahlzeiten laden |
| POST | /meals | Neue Mahlzeit erstellen |
| PUT | /meals/{id} | Mahlzeit bearbeiten |
| DELETE | /meals/{id} | Mahlzeit löschen |

## Backend starten (lokal)

```bash
./gradlew bootRun
```

Backend läuft auf: http://localhost:8080

## Tests ausführen

```bash
./gradlew test
```

## Live Backend

https://nutritrack-backend-1.onrender.com