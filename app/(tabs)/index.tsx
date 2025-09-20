import { Image } from 'expo-image';
import { Platform, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';
import { useState, useEffect } from 'react';

import { HelloWave } from '@/components/hello-wave';
import ParallaxScrollView from '@/components/parallax-scroll-view';
import { ThemedText } from '@/components/themed-text';
import { ThemedView } from '@/components/themed-view';

interface Destination {
  id: string;
  name: string;
  description: string;
  image: string;
  attractions: string[];
  rating: number;
}

export default function HomeScreen() {
  const [destinations, setDestinations] = useState<Destination[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchDestinations();
  }, []);

  const fetchDestinations = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/trips/destinations');
      const data = await response.json();
      if (data.success) {
        setDestinations(data.destinations);
      }
    } catch (error) {
      console.log('Error fetching destinations:', error);
      // For demo, if backend is not available, show static data
      setDestinations([
        {
          id: 'paris',
          name: 'Paris, France',
          description: 'The City of Light',
          image: 'paris.jpg',
          attractions: ['Eiffel Tower', 'Louvre Museum', 'Notre-Dame'],
          rating: 4.5
        }
      ]);
    } finally {
      setLoading(false);
    }
  };

  return (
    <ParallaxScrollView
      headerBackgroundColor={{ light: '#E6F4FE', dark: '#1D3D47' }}
      headerImage={
        <ThemedView style={styles.headerContainer}>
          <ThemedText style={styles.headerTitle}>🧳 Trip Genie</ThemedText>
          <ThemedText style={styles.headerSubtitle}>Your Travel Companion</ThemedText>
        </ThemedView>
      }>
      <ThemedView style={styles.titleContainer}>
        <ThemedText type="title">Welcome to Trip Genie!</ThemedText>
        <HelloWave />
      </ThemedView>
      
      <ThemedView style={styles.stepContainer}>
        <ThemedText type="subtitle">✈️ Popular Destinations</ThemedText>
        <ThemedText>
          Discover amazing places around the world and plan your perfect trip.
        </ThemedText>
      </ThemedView>

      {loading ? (
        <ThemedView style={styles.stepContainer}>
          <ThemedText>Loading destinations...</ThemedText>
        </ThemedView>
      ) : (
        <ScrollView style={styles.destinationsContainer}>
          {destinations.map((destination) => (
            <TouchableOpacity key={destination.id} style={styles.destinationCard}>
              <ThemedView style={styles.cardContent}>
                <ThemedText type="defaultSemiBold" style={styles.destinationName}>
                  {destination.name}
                </ThemedText>
                <ThemedText style={styles.destinationDescription}>
                  {destination.description}
                </ThemedText>
                <ThemedText style={styles.rating}>
                  ⭐ {destination.rating.toFixed(1)}
                </ThemedText>
                <ThemedText style={styles.attractions}>
                  Top attractions: {destination.attractions.slice(0, 2).join(', ')}
                </ThemedText>
              </ThemedView>
            </TouchableOpacity>
          ))}
        </ScrollView>
      )}

      <ThemedView style={styles.stepContainer}>
        <ThemedText type="subtitle">🚀 Demo Features</ThemedText>
        <ThemedText>
          This is a demo version of Trip Genie showcasing:
        </ThemedText>
        <ThemedText style={styles.featureText}>• Browse travel destinations</ThemedText>
        <ThemedText style={styles.featureText}>• Plan your trips</ThemedText>
        <ThemedText style={styles.featureText}>• User authentication (demo mode)</ThemedText>
        <ThemedText style={styles.featureText}>• Backend API integration</ThemedText>
      </ThemedView>

      <ThemedView style={styles.stepContainer}>
        <ThemedText type="subtitle">🛠️ Technical Stack</ThemedText>
        <ThemedText>
          Frontend: React Native + Expo{'\n'}
          Backend: Spring Boot + Java 17{'\n'}
          Database: H2 (in-memory for demo)
        </ThemedText>
      </ThemedView>
    </ParallaxScrollView>
  );
}

const styles = StyleSheet.create({
  headerContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20,
  },
  headerTitle: {
    fontSize: 32,
    fontWeight: 'bold',
    textAlign: 'center',
    marginBottom: 8,
  },
  headerSubtitle: {
    fontSize: 16,
    textAlign: 'center',
    opacity: 0.8,
  },
  titleContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
  },
  stepContainer: {
    gap: 8,
    marginBottom: 16,
  },
  destinationsContainer: {
    marginBottom: 16,
  },
  destinationCard: {
    marginBottom: 12,
    borderRadius: 12,
    overflow: 'hidden',
    backgroundColor: 'rgba(255, 255, 255, 0.1)',
    borderWidth: 1,
    borderColor: 'rgba(255, 255, 255, 0.2)',
  },
  cardContent: {
    padding: 16,
  },
  destinationName: {
    fontSize: 18,
    marginBottom: 4,
  },
  destinationDescription: {
    marginBottom: 8,
    opacity: 0.8,
  },
  rating: {
    marginBottom: 4,
    fontWeight: '600',
  },
  attractions: {
    fontSize: 12,
    opacity: 0.7,
  },
  featureText: {
    marginLeft: 8,
    marginTop: 2,
  },
});
