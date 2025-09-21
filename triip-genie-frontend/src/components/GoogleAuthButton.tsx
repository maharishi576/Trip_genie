// src/components/GoogleAuthButton.tsx
import React from "react";
import { Image, StyleSheet } from "react-native";
import { Button } from "react-native-paper";

type Props = { onPress: () => void };

export default function GoogleAuthButton({ onPress }: Props) {
  return (
    <Button
      mode="outlined"
      onPress={onPress}
      style={styles.button}
      contentStyle={styles.content}
      labelStyle={styles.label}
    >
      <Image source={require("../../assets/images/google.png")} style={styles.icon} />
      Continue with Google
    </Button>
  );
}

const styles = StyleSheet.create({
  button: {
    borderRadius: 8,
    marginBottom: 20,
    borderColor: "#ccc",
  },
  content: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
    paddingVertical: 6,
  },
  icon: { width: 20, height: 20, marginRight: 8 },
  label: { fontSize: 16 },
});
