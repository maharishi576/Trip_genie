export const API_BASE_URL = 'http://localhost:8080/api';

class TripService {
  async getAllTrips() {
    try {
      const response = await fetch(`${API_BASE_URL}/trips`);
      if (!response.ok) {
        throw new Error('Failed to fetch trips');
      }
      return await response.json();
    } catch (error) {
      console.error('Error fetching trips:', error);
      throw error;
    }
  }

  async getTripById(id) {
    try {
      const response = await fetch(`${API_BASE_URL}/trips/${id}`);
      if (!response.ok) {
        throw new Error('Failed to fetch trip');
      }
      return await response.json();
    } catch (error) {
      console.error('Error fetching trip:', error);
      throw error;
    }
  }

  async createTrip(trip) {
    try {
      const response = await fetch(`${API_BASE_URL}/trips`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(trip),
      });
      if (!response.ok) {
        throw new Error('Failed to create trip');
      }
      return await response.json();
    } catch (error) {
      console.error('Error creating trip:', error);
      throw error;
    }
  }

  async updateTrip(id, trip) {
    try {
      const response = await fetch(`${API_BASE_URL}/trips/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(trip),
      });
      if (!response.ok) {
        throw new Error('Failed to update trip');
      }
      return await response.json();
    } catch (error) {
      console.error('Error updating trip:', error);
      throw error;
    }
  }

  async deleteTrip(id) {
    try {
      const response = await fetch(`${API_BASE_URL}/trips/${id}`, {
        method: 'DELETE',
      });
      if (!response.ok) {
        throw new Error('Failed to delete trip');
      }
    } catch (error) {
      console.error('Error deleting trip:', error);
      throw error;
    }
  }

  async searchTrips(destination, maxCost) {
    try {
      const params = new URLSearchParams();
      if (destination) params.append('destination', destination);
      if (maxCost) params.append('maxCost', maxCost.toString());
      
      const response = await fetch(`${API_BASE_URL}/trips/search?${params}`);
      if (!response.ok) {
        throw new Error('Failed to search trips');
      }
      return await response.json();
    } catch (error) {
      console.error('Error searching trips:', error);
      throw error;
    }
  }
}

export default new TripService();