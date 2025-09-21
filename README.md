# Trip Genie - Full-Stack Travel Application

A complete travel guide application demonstrating how to structure a repository with both React Native frontend and Java Spring Boot backend in a working state.

## 🏗️ Architecture Overview

This project demonstrates a **monorepo structure** with both frontend and backend code:

```
Trip_genie/
├── trip-genie-frontend/     # React Native mobile app
├── trip-genie-backend/      # Java Spring Boot REST API
├── package.json             # Root package.json for scripts
├── README.md               # This file
└── docs/                   # Documentation
```

## 🚀 Quick Start

### Prerequisites
- **Node.js** 18+ and npm
- **Java** 17+
- **React Native CLI** (`npm install -g @react-native-community/cli`)
- **Android Studio** (for Android development)
- **Xcode** (for iOS development, macOS only)

### 1. Install Dependencies

```bash
# Install root dependencies
npm install

# Install frontend dependencies
npm run install:frontend

# Install backend dependencies (resolve Maven dependencies)
npm run install:backend
```

### 2. Start Both Services

```bash
# Start both frontend and backend concurrently
npm start

# Or start them separately:
npm run start:backend    # Starts Spring Boot on http://localhost:8080
npm run start:frontend   # Starts React Native Metro bundler
```

### 3. Run the Mobile App

```bash
# For Android (make sure you have an emulator running or device connected)
cd trip-genie-frontend && npx react-native run-android

# For iOS (macOS only)
cd trip-genie-frontend && npx react-native run-ios
```

## 📱 Frontend (React Native)

**Location**: `trip-genie-frontend/`

### Features
- ✅ Modern React Native app with TypeScript
- ✅ Trip listing with beautiful UI
- ✅ Integration with backend API
- ✅ CRUD operations for trips
- ✅ Search and filter functionality
- ✅ Error handling and loading states

### Key Files
- `App.tsx` - Main app component
- `src/services/TripService.js` - API integration
- `src/components/TripCard.js` - Reusable trip card component
- `src/screens/TripListScreen.js` - Main trip listing screen

### API Integration
The frontend connects to the backend API at `http://localhost:8080/api/trips`

## 🔧 Backend (Java Spring Boot)

**Location**: `trip-genie-backend/`

### Features
- ✅ RESTful API with Spring Boot
- ✅ CRUD operations for trips
- ✅ Search functionality
- ✅ CORS configuration for React Native
- ✅ In-memory data storage (easily replaceable with database)

### API Endpoints
- `GET /api/trips` - Get all trips
- `GET /api/trips/{id}` - Get trip by ID
- `POST /api/trips` - Create new trip
- `PUT /api/trips/{id}` - Update trip
- `DELETE /api/trips/{id}` - Delete trip
- `GET /api/trips/search?destination=...&maxCost=...` - Search trips

### Key Files
- `TripGenieBackendApplication.java` - Main Spring Boot application
- `controller/TripController.java` - REST API endpoints
- `model/Trip.java` - Trip data model

## 🛠️ Development Scripts

### Root Level Commands
```bash
npm start                 # Start both frontend and backend
npm test                  # Run all tests
npm run build:frontend    # Build React Native app
npm run build:backend     # Build Spring Boot JAR
```

### Frontend Commands
```bash
cd trip-genie-frontend
npm start                 # Start Metro bundler
npm test                  # Run Jest tests
npm run android          # Run on Android
npm run ios              # Run on iOS
```

### Backend Commands
```bash
cd trip-genie-backend
./mvnw spring-boot:run   # Start Spring Boot server
./mvnw test              # Run tests
./mvnw package           # Build JAR file
```

## 🧪 Testing

### Backend Tests
```bash
npm run test:backend
# or
cd trip-genie-backend && ./mvnw test
```

### Frontend Tests
```bash
npm run test:frontend
# or
cd trip-genie-frontend && npm test
```

## 📦 Building for Production

### Backend JAR
```bash
cd trip-genie-backend
./mvnw clean package
# Creates: target/trip-genie-backend-0.0.1-SNAPSHOT.jar
```

### React Native APK/IPA
```bash
cd trip-genie-frontend
# For Android
npx react-native build-android
# For iOS
npx react-native build-ios
```

## 🚢 Deployment Options

### Option 1: Docker (Recommended)
```bash
# Backend Dockerfile
cd trip-genie-backend
docker build -t trip-genie-backend .
docker run -p 8080:8080 trip-genie-backend

# Frontend can be built as APK/IPA for app stores
```

### Option 2: Cloud Deployment
- **Backend**: Deploy to Heroku, AWS, Google Cloud, etc.
- **Frontend**: Publish to Google Play Store, Apple App Store

## 🔗 Integration Points

### How Frontend Connects to Backend
1. **TripService.js** handles all API calls
2. **API_BASE_URL** can be configured for different environments
3. **CORS** is enabled in Spring Boot for cross-origin requests
4. **Error handling** implemented for network failures

### Sample API Response
```json
{
  "id": 1,
  "title": "Tokyo Adventure",
  "destination": "Tokyo, Japan",
  "description": "Explore the vibrant culture and technology of Tokyo",
  "startDate": "2024-03-15",
  "endDate": "2024-03-22",
  "estimatedCost": 2500.0
}
```

## 🎯 Project Structure Benefits

This monorepo structure provides:

1. **Single Repository**: Both frontend and backend code in one place
2. **Consistent Tooling**: Shared scripts and configuration
3. **Easy Development**: Start both services with one command
4. **Version Control**: Changes to both parts tracked together
5. **Documentation**: Comprehensive setup in one README

## 🔧 Configuration

### Backend Configuration
- **Port**: 8080 (configurable in `application.properties`)
- **CORS**: Enabled for all origins (configure for production)
- **Database**: In-memory (easily replaceable with JPA/H2/PostgreSQL)

### Frontend Configuration
- **API URL**: `http://localhost:8080/api` (configurable)
- **Platform**: Android/iOS compatible
- **Navigation**: Currently using Alert for demo (easily replaceable with React Navigation)

## 🤝 Contributing

1. Clone the repository
2. Install dependencies: `npm install`
3. Start development servers: `npm start`
4. Make your changes
5. Test: `npm test`
6. Submit a pull request

## 📄 License

MIT License - feel free to use this project structure for your own applications!

## 🎉 What's Next?

This project demonstrates the foundation. You can extend it with:

- 🗄️ Database integration (PostgreSQL, MongoDB)
- 🔐 Authentication and user management
- 🗺️ Maps and location services
- 📸 Photo uploads for trips
- 🔔 Push notifications
- 🌐 Web frontend (React.js)
- 📊 Analytics and reporting
- 🔄 Real-time updates (WebSocket)

---

**Example of a working full-stack architecture with React Native + Java Spring Boot** 🚀