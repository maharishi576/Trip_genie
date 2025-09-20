# Trip Genie - Travel Planning Application

![Trip Genie Logo](🧳)

A modern travel planning application with React Native frontend and Spring Boot backend.

## 📱 Demo Features

- **Browse Destinations**: Explore popular travel destinations with ratings and attractions
- **Trip Planning**: Plan your trips with dates, travelers, and budget
- **User Authentication**: Demo signup/login functionality
- **Real-time API**: Frontend connected to Spring Boot backend
- **Responsive Design**: Works on web, iOS, and Android

## 🛠️ Technical Stack

### Frontend
- **React Native** with **Expo**
- **TypeScript** for type safety
- **Expo Router** for navigation
- Cross-platform (Web, iOS, Android)

### Backend
- **Spring Boot 3.2.12** with **Java 17**
- **H2 Database** (in-memory for demo)
- **Spring Security** (disabled for demo)
- **RESTful APIs** with CORS enabled

## 🚀 Quick Start

### Prerequisites
- Node.js 20+ and npm
- Java 17+
- Git

### Running the Demo

1. **Clone and navigate to the repository**:
   ```bash
   git clone <repository-url>
   cd Trip_genie
   ```

2. **Install frontend dependencies**:
   ```bash
   npm install
   ```

3. **Start the demo** (automated script):
   ```bash
   ./start-demo.sh
   ```

4. **Or start services manually**:

   **Backend** (Terminal 1):
   ```bash
   cd trip-genie-backend
   ./mvnw spring-boot:run
   ```

   **Frontend** (Terminal 2):
   ```bash
   npm run web
   ```

### 🌐 Access Points

Once running, you can access:

- **Frontend Web App**: http://localhost:19006
- **Backend API**: http://localhost:8080
- **Health Check**: http://localhost:8080/api/trips/health
- **H2 Database Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:demodb`
  - Username: `sa`
  - Password: `password`

## 📱 Mobile Development

### iOS/Android Development
```bash
# For iOS simulator
npm run ios

# For Android emulator  
npm run android

# For development build
npm run start
```

## 🔧 API Endpoints

### Trips
- `GET /api/trips/destinations` - Get all destinations
- `GET /api/trips/destinations/{id}` - Get specific destination
- `POST /api/trips/plan` - Plan a new trip
- `GET /api/trips/health` - Health check

### Authentication (Demo Mode)
- `POST /api/auth/signup/request-otp` - Request OTP
- `POST /api/auth/signup/verify-otp` - Verify OTP and register

## 📝 Demo Data

The application includes sample destinations:
- Paris, France
- Tokyo, Japan
- New York, USA
- London, UK
- Bali, Indonesia

## 🔄 Development Workflow

1. **Backend Changes**: Automatic restart with Spring Boot DevTools
2. **Frontend Changes**: Hot reload with Expo
3. **Database**: H2 in-memory (resets on restart)
4. **CORS**: Configured for localhost:19006 and localhost:3000

## 📊 Project Structure

```
Trip_genie/
├── trip-genie-backend/          # Spring Boot backend
│   ├── src/main/java/           # Java source code
│   ├── src/main/resources/      # Configuration files
│   └── pom.xml                  # Maven dependencies
├── app/                         # React Native app
│   ├── (tabs)/                  # Tab navigation screens
│   └── _layout.tsx              # Root layout
├── components/                  # Reusable UI components
├── constants/                   # App constants
├── package.json                 # Frontend dependencies
└── start-demo.sh               # Demo startup script
```

## 🚀 Production Deployment

For production deployment:

1. **Backend**: 
   - Replace H2 with production database (MySQL/PostgreSQL)
   - Enable Spring Security
   - Configure production profiles
   - Set environment variables

2. **Frontend**:
   - Build for production: `npm run build`
   - Deploy to hosting service
   - Update API endpoints

## 🛡️ Security Notes

- **Demo Mode**: Authentication is simplified for demonstration
- **CORS**: Currently allows all localhost origins
- **Database**: Using in-memory H2 (data doesn't persist)
- **Passwords**: BCrypt hashing is configured but not used in demo

## 🐛 Troubleshooting

### Backend Issues
- **Port 8080 in use**: Change port in `application.properties`
- **Java version**: Ensure Java 17+ is installed
- **Maven issues**: Use `./mvnw clean install`

### Frontend Issues
- **Metro bundler issues**: Clear cache with `npx expo start --clear`
- **Port conflicts**: Expo will find alternative ports
- **Network issues**: Check firewall settings

### API Connection Issues
- Ensure backend is running on port 8080
- Check CORS configuration in `CorsConfig.java`
- Verify API URLs in frontend code

## 📈 Next Steps for Full Production

1. **User Authentication**: Implement full JWT-based auth
2. **Database**: Set up persistent database with migrations
3. **Payment Integration**: Add booking and payment features
4. **Email Service**: Implement real email/SMS for OTP
5. **Push Notifications**: Add mobile notifications
6. **Offline Support**: Cache data for offline use
7. **Testing**: Add comprehensive test suites
8. **CI/CD**: Set up automated deployment pipelines

## 📞 Support

For development questions or issues:
- Check the console logs
- Verify all services are running
- Ensure ports 8080 and 19006 are available
- Review this README for common solutions
