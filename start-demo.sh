#!/bin/bash

# Trip Genie Demo Startup Script
echo "🧳 Starting Trip Genie Demo..."

# Start backend in background
echo "🚀 Starting backend server on port 8080..."
cd trip-genie-backend
./mvnw spring-boot:run &
BACKEND_PID=$!

# Wait for backend to start
echo "⏳ Waiting for backend to initialize..."
sleep 10

# Check if backend is running
if curl -s http://localhost:8080/api/trips/health > /dev/null; then
    echo "✅ Backend is running!"
else
    echo "❌ Backend failed to start"
    kill $BACKEND_PID
    exit 1
fi

# Start frontend
echo "🌐 Starting frontend on port 19006..."
cd ..
npm run web &
FRONTEND_PID=$!

echo "📱 Trip Genie Demo is starting..."
echo "🌐 Frontend: http://localhost:19006"
echo "🚀 Backend API: http://localhost:8080"
echo "🗄️ H2 Database Console: http://localhost:8080/h2-console"
echo ""
echo "Press Ctrl+C to stop all services"

# Function to cleanup processes on exit
cleanup() {
    echo "🛑 Stopping services..."
    kill $BACKEND_PID $FRONTEND_PID 2>/dev/null
    exit 0
}

# Set trap to cleanup on script exit
trap cleanup SIGINT SIGTERM

# Wait for any of the processes to exit
wait