# Quick Start Guide

## Overview
This repository demonstrates a complete full-stack architecture with:
- **React Native** frontend (mobile app)
- **Java Spring Boot** backend (REST API)
- **Monorepo structure** with both codebases

## Prerequisites
- Node.js 18+ and npm
- Java 17+
- Android Studio (for Android development)
- Xcode (for iOS development, macOS only)

## Quick Start (< 5 minutes)

### 1. Clone and Install
```bash
git clone https://github.com/maharishi576/Trip_genie.git
cd Trip_genie
npm install
```

### 2. Start Backend API
```bash
npm run start:backend
```
Backend will start on http://localhost:8080

### 3. Test API (in another terminal)
```bash
curl http://localhost:8080/api/trips
```
You should see JSON response with 3 sample trips.

### 4. Start React Native App
```bash
npm run start:frontend
```

### 5. Run Mobile App
```bash
# For Android (ensure emulator is running)
cd trip-genie-frontend && npx react-native run-android

# For iOS (macOS only)
cd trip-genie-frontend && npx react-native run-ios
```

## Project Structure
```
Trip_genie/
├── trip-genie-backend/     # Java Spring Boot API
│   ├── src/main/java/
│   │   └── controller/     # REST endpoints
│   │   └── model/         # Data models
│   └── pom.xml            # Maven dependencies
├── trip-genie-frontend/    # React Native app
│   ├── src/
│   │   ├── components/    # UI components
│   │   ├── screens/       # App screens
│   │   └── services/      # API integration
│   └── package.json       # NPM dependencies
├── package.json            # Root scripts
└── README.md              # Full documentation
```

## Key Features

### Backend API
- ✅ GET /api/trips - List all trips
- ✅ POST /api/trips - Create trip
- ✅ PUT /api/trips/{id} - Update trip
- ✅ DELETE /api/trips/{id} - Delete trip
- ✅ GET /api/trips/search - Search trips

### Frontend App
- ✅ Trip listing with beautiful cards
- ✅ API integration with error handling
- ✅ Loading states and refresh functionality
- ✅ Ready for navigation and new features

## Development Scripts
```bash
npm start                  # Start both frontend and backend
npm test                   # Run all tests
npm run build:backend      # Build Spring Boot JAR
npm run build:frontend     # Build React Native app
```

## Next Steps
1. Add authentication and user management
2. Integrate with real database (PostgreSQL, MongoDB)
3. Add maps and location services
4. Implement push notifications
5. Deploy to cloud platforms

This is a complete, working example perfect for learning or as a starting point for your own full-stack mobile application! 🚀