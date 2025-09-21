import { useNavigation } from "@react-navigation/native";
import type { NativeStackNavigationProp } from "@react-navigation/native-stack";
import React from "react";
import { ImageBackground, StyleSheet, Text, View } from "react-native";
import { Button } from "react-native-paper";
import type { RootStackParamList } from "../types/navigation";

type NavProp = NativeStackNavigationProp<RootStackParamList, "MainPage">;

export default function MainPage() {
  const navigation = useNavigation<NavProp>();

  return (
    <View style={styles.container}>
      <ImageBackground source={require("../../assets/images/lgs.jpg")} style={styles.bg} resizeMode="cover">
        <View style={styles.bottomContainer}>
          <View style={styles.overlay}>
            <Text style={styles.heading}>Your Journey,</Text>
            <Text style={styles.heading}>Our Magic</Text>

            <View style={{ paddingTop: 16 }}>
              <Text style={styles.subText}>Personalized itineraries,</Text>
              <Text style={styles.subText}>offline-ready maps,</Text>
              <Text style={styles.subText}>and a 24/7 live guide — all in your pocket</Text>
            </View>

            <Button mode="contained" onPress={() => navigation.navigate("LogIn")} style={styles.button} labelStyle={styles.buttonLabel}>
              Lets Get Started
            </Button>
          </View>
        </View>
      </ImageBackground>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: "#000" },
  bg: { flex: 1, justifyContent: "center", alignItems: "center" },
  bottomContainer: { flex: 1, width: "100%", justifyContent: "flex-end", paddingLeft: 24, paddingRight: 24, paddingBottom: 40 },
  overlay: { paddingBottom: 40 },
  heading: { color: "white", fontWeight: "600", fontSize: 24, lineHeight: 30 },
  subText: { color: "white", fontWeight: "400", fontSize: 16, lineHeight: 24 },
  button: { marginTop: 48, backgroundColor: "#EDEDED", paddingVertical: 8, borderRadius: 8 },
  buttonLabel: { color: "#3B3B3B", fontSize: 16, fontWeight: "700" as any },
});
