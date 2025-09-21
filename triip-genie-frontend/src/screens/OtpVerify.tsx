// src/screens/OtpVerify.tsx
import React, { useState } from "react";
import { View, StyleSheet, Alert, ActivityIndicator } from "react-native";
import { Text, TextInput } from "react-native-paper";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import { RouteProp, useNavigation, useRoute } from "@react-navigation/native";
import type { RootStackParamList } from "../types/navigation";
import { verifyOtp } from "../config/api";
import AsyncStorage from "@react-native-async-storage/async-storage";
import GradientButton from "../components/GradientButton";

type NavProp = NativeStackNavigationProp<RootStackParamList, "OtpVerify">;
type Route = RouteProp<RootStackParamList, "OtpVerify">;

export default function OtpVerify() {
  const navigation = useNavigation<NavProp>();
  const route = useRoute<Route>();
  const { email, name, mobile } = route.params ?? { email: "", name: "", mobile: "" };

  const [otp, setOtp] = useState("");
  const [loading, setLoading] = useState(false);

  const handleVerify = async () => {
    if (!otp.trim()) return Alert.alert("Validation", "Please enter the OTP.");
    setLoading(true);
    try {
      const res = await verifyOtp({ email, otp: otp.trim(), name, mobile });
      setLoading(false);
      if (res?.success) {
        // save user returned by backend
        await AsyncStorage.setItem("user", JSON.stringify(res.user));
        // reset navigation so user can't go back to auth screens
        navigation.reset({
          index: 0,
          routes: [{ name: "Home" }],
        });
      } else {
        Alert.alert("Verification failed", res?.message || "OTP invalid");
      }
    } catch (err: any) {
      setLoading(false);
      Alert.alert("Error", err?.message || "Network error");
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Verify OTP</Text>
      <Text style={styles.subtitle}>We sent an OTP to {email}</Text>

      <TextInput label="OTP" mode="outlined" value={otp} onChangeText={setOtp} keyboardType="numeric" style={styles.input} />

      {loading ? <ActivityIndicator size="large" style={{ marginTop: 12 }} /> : <GradientButton title="Verify & Register" onPress={handleVerify} />}
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 20, justifyContent: "center" },
  title: { fontSize: 22, fontWeight: "700", marginBottom: 8 },
  subtitle: { fontSize: 14, color: "#666", marginBottom: 20 },
  input: { marginBottom: 16 },
});
