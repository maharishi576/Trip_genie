import { ImageBackground, StyleSheet, View } from "react-native";
import { Text } from "react-native-paper";

export default function TrendingCard({ title, subtitle, travelers, image }) {
  return (
    <ImageBackground
      source={image}
      style={styles.card}
      imageStyle={{ borderRadius: 12 }}
    >
      <View style={styles.overlay}>
        <Text style={styles.title}>{title}</Text>
        <Text style={styles.subtitle}>{subtitle}</Text>
        <Text style={styles.travelers}>{travelers} travelers now</Text>
      </View>
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
  card: {
    width: 200,
    height: 200,
    marginRight: 12,
    borderRadius: 12,
    overflow: "hidden",
    justifyContent: "flex-end",
  },
  overlay: {
    padding: 10,
    backgroundColor: "rgba(0,0,0,0.4)", // dark overlay for text readability
    borderBottomLeftRadius: 12,
    borderBottomRightRadius: 12,
  },
  title: {
    color: "#fff",
    fontSize: 16,
    fontWeight: "bold",
  },
  subtitle: {
    color: "#ddd",
    fontSize: 12,
    marginTop: 2,
  },
  travelers: {
    color: "#fff",
    fontSize: 12,
    marginTop: 4,
    fontWeight: "600",
  },
});
