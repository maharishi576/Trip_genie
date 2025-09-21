import React from "react";
import { View, StyleSheet } from "react-native";
import { Avatar, Text } from "react-native-paper";
import { MaterialIcons } from "@expo/vector-icons";

export default function TestimonialCard({ name, review, description, rating, image }) {
  // Render stars dynamically
  const renderStars = () => {
    const stars = [];
    for (let i = 1; i <= 5; i++) {
      stars.push(
        <MaterialIcons
          key={i}
          name={i <= rating ? "star" : "star-border"}
          size={20}
          color="#1F6F9A"
        />
      );
    }
    return stars;
  };

  return (
    <View style={styles.card}>
      {/* User Avatar */}
      <Avatar.Image size={70} source={image} style={styles.avatar} />

      {/* Name */}
      <Text style={styles.name}>{name}</Text>

      {/* Stars */}
      <View style={styles.stars}>{renderStars()}</View>

      {/* Review Title */}
      <Text style={styles.reviewTitle}>"{review}"</Text>

      {/* Review Description */}
      <Text style={styles.description}>
        {description} <Text style={styles.readMore}>Read More</Text>
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  card: {
    width: 250,
    padding: 15,
    marginRight: 15,
    borderRadius: 15,
    backgroundColor: "#fff",
    shadowColor: "#000",
    shadowOpacity: 0.1,
    shadowOffset: { width: 0, height: 3 },
    shadowRadius: 5,
    elevation: 3,
    alignItems: "center",
  },
  avatar: { marginBottom: 10 },
  name: { fontWeight: "bold", fontSize: 16, marginBottom: 5 },
  stars: { flexDirection: "row", marginBottom: 8 },
  reviewTitle: { fontSize: 14, fontWeight: "600", textAlign: "center", marginBottom: 6 },
  description: { fontSize: 12, color: "#666", textAlign: "center" },
  readMore: { color: "#1F6F9A", fontWeight: "bold" },
});
