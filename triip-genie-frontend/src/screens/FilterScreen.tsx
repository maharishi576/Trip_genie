import React, { useState } from "react";
import { View, ScrollView, StyleSheet, TouchableOpacity } from "react-native";
import { Text, Chip, Avatar } from "react-native-paper";
import { Ionicons } from "@expo/vector-icons";

export default function FilterScreen({ navigation }) {
  const [selected, setSelected] = useState<string[]>([
    "City Exploration",
    "Historical Sites",
    "Hiking",
    "Local Street Food",
    "North Indian",
  ]);

  const toggleSelect = (item: string) => {
    setSelected((prev) =>
      prev.includes(item) ? prev.filter((i) => i !== item) : [...prev, item]
    );
  };

  // Data Groups
  const tripTypes = [
    "City Exploration", "Historical Sites", "Cafes", "City nightlife",
    "Trekking", "Camping", "Parks", "Natural trails", "Paragliding",
    "Water Sports", "River rafting", "Yoga", "Scenic Drives", "Coast"
  ];

  const activities = [
    "Hiking", "Rock Climbing", "Skiing", "Streets", "Museums", "Local Festivals",
    "Theatre Shows", "Concerts", "Beaches", "Spas", "Local Markets",
    "Souvenir Hunting", "Wildlife", "Botanical Gardens", "National Parks",
    "Scenic Viewpoint", "Bars", "Pubs", "Nightclubs", "Live Music", "Instagrammable"
  ];

  const food = [
    "Local Street Food", "North Indian", "South Indian", "Multi-Cuisine",
    "Veg", "Non-Veg", "Jain", "Halal", "Fine Dining", "Dhabas",
    "Family Restaurants", "Cafes"
  ];

  return (
    <View style={styles.container}>
      {/* Header */}
      <View style={styles.header}>
        <TouchableOpacity onPress={() => navigation.goBack()}>
          <Ionicons name="arrow-back" size={24} color="#1F6F9A" />
        </TouchableOpacity>
        <View style={styles.loaderCircle}>
          <Avatar.Icon size={32} icon="loading" color="#1F6F9A" style={{ backgroundColor: "transparent" }} />
        </View>
      </View>

      <ScrollView showsVerticalScrollIndicator={false}>
        {/* Title */}
        <Text style={styles.title}>Customize Your Preferences</Text>

        {/* Trip Type */}
        <Text style={styles.section}>Trip Type and Vibe</Text>
        <View style={styles.chipWrapper}>
          {tripTypes.map((item) => (
            <Chip
              key={item}
              style={[
                styles.chip,
                selected.includes(item) && { backgroundColor: "#1F6F9A" },
              ]}
              textStyle={{ color: selected.includes(item) ? "#fff" : "#555" }}
              selected={selected.includes(item)}
              onPress={() => toggleSelect(item)}
            >
              {item}
            </Chip>
          ))}
        </View>

        {/* Interest & Activity */}
        <Text style={styles.section}>Interest & Activity</Text>
        <View style={styles.chipWrapper}>
          {activities.map((item) => (
            <Chip
              key={item}
              style={[
                styles.chip,
                selected.includes(item) && { backgroundColor: "#1F6F9A" },
              ]}
              textStyle={{ color: selected.includes(item) ? "#fff" : "#555" }}
              selected={selected.includes(item)}
              onPress={() => toggleSelect(item)}
            >
              {item}
            </Chip>
          ))}
        </View>

        {/* Food & Dining */}
        <Text style={styles.section}>Food & Dining</Text>
        <View style={styles.chipWrapper}>
          {food.map((item) => (
            <Chip
              key={item}
              style={[
                styles.chip,
                selected.includes(item) && { backgroundColor: "#1F6F9A" },
              ]}
              textStyle={{ color: selected.includes(item) ? "#fff" : "#555" }}
              selected={selected.includes(item)}
              onPress={() => toggleSelect(item)}
            >
              {item}
            </Chip>
          ))}
        </View>
      </ScrollView>

      {/* Bottom Navigation */}
      {/* <View style={styles.bottomNav}>
        <Ionicons name="home-outline" size={24} color="#1F6F9A" />
        <Ionicons name="map-outline" size={24} color="#999" />
        <Ionicons name="calendar-outline" size={24} color="#999" />
        <Ionicons name="person-outline" size={24} color="#999" />
      </View> */}
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: "#f9f9f9" },
  header: {
    flexDirection: "row",
    justifyContent: "space-between",
    padding: 15,
    alignItems: "center",
  },
  loaderCircle: {
    width: 36,
    height: 36,
    borderRadius: 18,
    borderWidth: 3,
    borderColor: "#1F6F9A",
    borderTopColor: "transparent",
    justifyContent: "center",
    alignItems: "center",
  },
  title: { fontSize: 20, fontWeight: "bold", paddingHorizontal: 15, marginBottom: 15 },
  section: {
    fontSize: 16,
    fontWeight: "600",
    marginTop: 15,
    marginBottom: 10,
    paddingHorizontal: 15,
  },
  chipWrapper: {
    flexDirection: "row",
    flexWrap: "wrap",
    paddingHorizontal: 15,
    marginBottom: 10,
  },
  chip: {
    margin: 5,
    backgroundColor: "#eee",
    borderRadius: 20,
  },
  bottomNav: {
    flexDirection: "row",
    justifyContent: "space-around",
    alignItems: "center",
    paddingVertical: 12,
    borderTopWidth: 1,
    borderColor: "#ddd",
    backgroundColor: "#fff",
  },
});
