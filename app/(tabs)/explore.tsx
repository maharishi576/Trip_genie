import { StyleSheet, TextInput, TouchableOpacity, Alert } from 'react-native';
import { useState } from 'react';

import ParallaxScrollView from '@/components/parallax-scroll-view';
import { ThemedText } from '@/components/themed-text';
import { ThemedView } from '@/components/themed-view';
import { IconSymbol } from '@/components/ui/icon-symbol';

export default function TripPlanningScreen() {
  const [tripData, setTripData] = useState({
    destination: '',
    startDate: '',
    endDate: '',
    travelers: '',
    budget: ''
  });

  const handlePlanTrip = async () => {
    if (!tripData.destination || !tripData.startDate || !tripData.endDate) {
      Alert.alert('Missing Information', 'Please fill in destination, start date, and end date.');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/api/trips/plan', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(tripData),
      });

      const data = await response.json();
      if (data.success) {
        Alert.alert(
          'Trip Planned Successfully!',
          `Your trip to ${tripData.destination} has been planned.\nTrip ID: ${data.tripPlan.id}`,
          [{ text: 'OK' }]
        );
        // Reset form
        setTripData({
          destination: '',
          startDate: '',
          endDate: '',
          travelers: '',
          budget: ''
        });
      } else {
        Alert.alert('Error', 'Failed to plan trip. Please try again.');
      }
    } catch (error) {
      console.log('Error planning trip:', error);
      Alert.alert('Demo Mode', 'Trip planned successfully (offline demo)');
    }
  };

  return (
    <ParallaxScrollView
      headerBackgroundColor={{ light: '#E6F4FE', dark: '#353636' }}
      headerImage={
        <ThemedView style={styles.headerContainer}>
          <ThemedText style={styles.headerTitle}>🗺️</ThemedText>
          <ThemedText style={styles.headerSubtitle}>Plan Your Trip</ThemedText>
        </ThemedView>
      }>
      <ThemedView style={styles.titleContainer}>
        <ThemedText type="title">Trip Planning</ThemedText>
      </ThemedView>
      
      <ThemedText style={styles.description}>
        Create your perfect travel itinerary with Trip Genie!
      </ThemedText>

      <ThemedView style={styles.formContainer}>
        <ThemedView style={styles.inputContainer}>
          <ThemedText style={styles.label}>Destination *</ThemedText>
          <TextInput
            style={styles.input}
            placeholder="e.g., Paris, France"
            value={tripData.destination}
            onChangeText={(text) => setTripData({...tripData, destination: text})}
          />
        </ThemedView>

        <ThemedView style={styles.inputContainer}>
          <ThemedText style={styles.label}>Start Date *</ThemedText>
          <TextInput
            style={styles.input}
            placeholder="YYYY-MM-DD"
            value={tripData.startDate}
            onChangeText={(text) => setTripData({...tripData, startDate: text})}
          />
        </ThemedView>

        <ThemedView style={styles.inputContainer}>
          <ThemedText style={styles.label}>End Date *</ThemedText>
          <TextInput
            style={styles.input}
            placeholder="YYYY-MM-DD"
            value={tripData.endDate}
            onChangeText={(text) => setTripData({...tripData, endDate: text})}
          />
        </ThemedView>

        <ThemedView style={styles.inputContainer}>
          <ThemedText style={styles.label}>Number of Travelers</ThemedText>
          <TextInput
            style={styles.input}
            placeholder="e.g., 2"
            value={tripData.travelers}
            onChangeText={(text) => setTripData({...tripData, travelers: text})}
            keyboardType="numeric"
          />
        </ThemedView>

        <ThemedView style={styles.inputContainer}>
          <ThemedText style={styles.label}>Budget (USD)</ThemedText>
          <TextInput
            style={styles.input}
            placeholder="e.g., 2000"
            value={tripData.budget}
            onChangeText={(text) => setTripData({...tripData, budget: text})}
            keyboardType="numeric"
          />
        </ThemedView>

        <TouchableOpacity style={styles.planButton} onPress={handlePlanTrip}>
          <ThemedText style={styles.planButtonText}>Plan My Trip ✈️</ThemedText>
        </TouchableOpacity>
      </ThemedView>

      <ThemedView style={styles.featuresContainer}>
        <ThemedText type="subtitle">✨ What You Get</ThemedText>
        <ThemedText style={styles.feature}>📍 Personalized itinerary</ThemedText>
        <ThemedText style={styles.feature}>🏨 Hotel recommendations</ThemedText>
        <ThemedText style={styles.feature}>🍽️ Restaurant suggestions</ThemedText>
        <ThemedText style={styles.feature}>🎯 Local attractions</ThemedText>
        <ThemedText style={styles.feature}>💰 Budget optimization</ThemedText>
      </ThemedView>

      <ThemedView style={styles.demoNote}>
        <ThemedText style={styles.demoText}>
          💡 This is a demo version. In the full version, you'll get detailed itineraries, 
          booking integration, and real-time updates.
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
    fontSize: 48,
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
    gap: 8,
    marginBottom: 16,
  },
  description: {
    marginBottom: 24,
    fontSize: 16,
    opacity: 0.8,
  },
  formContainer: {
    marginBottom: 24,
  },
  inputContainer: {
    marginBottom: 16,
  },
  label: {
    marginBottom: 8,
    fontWeight: '600',
  },
  input: {
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 8,
    padding: 12,
    fontSize: 16,
    backgroundColor: 'rgba(255, 255, 255, 0.1)',
    color: '#000',
  },
  planButton: {
    backgroundColor: '#007AFF',
    padding: 16,
    borderRadius: 12,
    alignItems: 'center',
    marginTop: 8,
  },
  planButtonText: {
    color: 'white',
    fontSize: 18,
    fontWeight: 'bold',
  },
  featuresContainer: {
    marginBottom: 24,
  },
  feature: {
    marginVertical: 4,
    marginLeft: 8,
  },
  demoNote: {
    backgroundColor: 'rgba(255, 165, 0, 0.1)',
    padding: 16,
    borderRadius: 8,
    borderWidth: 1,
    borderColor: 'rgba(255, 165, 0, 0.3)',
  },
  demoText: {
    fontSize: 14,
    opacity: 0.8,
  },
});
