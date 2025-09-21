import React from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  View,
  TouchableOpacity,
  Alert,
} from 'react-native';
import TripListScreen from './src/screens/TripListScreen';

// Mock navigation for demo purposes
const mockNavigation = {
  navigate: (screen: string, params?: any) => {
    Alert.alert('Navigation', `Would navigate to ${screen}`, [
      { text: 'OK' }
    ]);
  },
};

function App(): React.JSX.Element {
  return (
    <SafeAreaView style={styles.container}>
      <StatusBar
        barStyle="dark-content"
        backgroundColor="#fff"
      />
      <View style={styles.header}>
        <Text style={styles.headerTitle}>🧳 Trip Genie</Text>
        <Text style={styles.headerSubtitle}>Your Travel Companion</Text>
      </View>
      
      <View style={styles.content}>
        <TripListScreen navigation={mockNavigation} />
      </View>
      
      <View style={styles.footer}>
        <Text style={styles.footerText}>
          Backend API: http://localhost:8080/api/trips
        </Text>
        <Text style={styles.footerNote}>
          Make sure the Spring Boot backend is running
        </Text>
      </View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f5f5f5',
  },
  header: {
    backgroundColor: '#2196F3',
    paddingVertical: 20,
    paddingHorizontal: 16,
    alignItems: 'center',
  },
  headerTitle: {
    fontSize: 28,
    fontWeight: 'bold',
    color: '#fff',
    marginBottom: 4,
  },
  headerSubtitle: {
    fontSize: 16,
    color: '#E3F2FD',
  },
  content: {
    flex: 1,
  },
  footer: {
    backgroundColor: '#fff',
    paddingVertical: 12,
    paddingHorizontal: 16,
    borderTopWidth: 1,
    borderTopColor: '#eee',
  },
  footerText: {
    fontSize: 12,
    color: '#666',
    textAlign: 'center',
  },
  footerNote: {
    fontSize: 10,
    color: '#999',
    textAlign: 'center',
    marginTop: 4,
  },
});

export default App;
