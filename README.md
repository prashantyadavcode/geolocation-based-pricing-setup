# Geo-pricing demo (Vite + Spring Boot)

This repo contains:

- `frontend/`: React + Vite (deploy to Vercel)
- `backend/`: Spring Boot (deploy to a JVM host like Render/Railway/Fly.io)

## Local development

### Backend

```bash
cd backend
./mvnw spring-boot:run
```

Backend runs on `http://localhost:8080`.

### Frontend

```bash
cd frontend
npm install
npm run dev
```

Frontend runs on `http://localhost:5173`.

## Deploy

### Frontend on Vercel

In Vercel, set:

- Root Directory: `frontend`
- Build Command: `npm run build`
- Output Directory: `dist`
- Environment Variable: `VITE_API_BASE_URL` = your deployed backend base URL (example: `https://your-backend.onrender.com`)

### Backend

Vercel doesn’t natively host Spring Boot/JVM servers. Deploy the backend to a JVM host (Render/Railway/Fly.io) and use that URL as `VITE_API_BASE_URL` in Vercel.
