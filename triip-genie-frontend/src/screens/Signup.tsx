// src/screens/Signup.tsx
import { useNavigation } from "@react-navigation/native";
import type { NativeStackNavigationProp } from "@react-navigation/native-stack";
import React, { useState } from "react";
import {
  ImageBackground,
  StyleSheet,
  TouchableOpacity,
  View,
  Alert,
  ActivityIndicator,
} from "react-native";
import { Checkbox, Text, TextInput } from "react-native-paper";
import GoogleAuthButton from "../components/GoogleAuthButton";
import GradientButton from "../components/GradientButton";
import type { RootStackParamList } from "../types/navigation";
import { requestOtp } from "../services/api";

type NavProp = NativeStackNavigationProp<RootStackParamList, "Signup">;

export default function Signup() {
  const navigation = useNavigation<NavProp>();
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [mobile, setMobile] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [agree, setAgree] = useState(false);
  const [loading, setLoading] = useState(false);

  const handleSignUp = async () => {
    // basic validation
    if (!name.trim() || !email.trim()) {
      return Alert.alert("Validation", "Please enter name and email.");
    }
    if (!agree) {
      return Alert.alert("Terms", "Please accept Terms and Conditions.");
    }

    setLoading(true);
    try {
      const res = await requestOtp({ email: email.trim(), name: name.trim(), mobile: mobile.trim() });
      // demo backend returns success + message
      setLoading(false);
      // navigate to OTP verify screen, pass email/name/mobile
      navigation.navigate("OtpVerify", { email: email.trim(), name: name.trim(), mobile: mobile.trim() });
    } catch (err: any) {
      setLoading(false);
      Alert.alert("Error", err?.message || "Failed to request OTP");
    }
  };

  return (
    <View style={styles.container}>
      <ImageBackground source={require("../../assets/images/LoginBanner.png")} style={styles.banner} resizeMode="cover">
        <View style={styles.bottomContainer}>
          <View style={styles.formContainer}>
            <Text style={styles.title}>Sign Up</Text>
            <Text style={styles.subtitle}>Lets Keep it Quick</Text>

            <TextInput label="Full Name" mode="outlined" value={name} onChangeText={setName} style={styles.input} />
            <TextInput label="Email" mode="outlined" keyboardType="email-address" value={email} onChangeText={setEmail} style={styles.input} />
            <TextInput label="Mobile No." mode="outlined" keyboardType="phone-pad" value={mobile} onChangeText={setMobile} style={styles.input} />
            <TextInput
              label="Password"
              mode="outlined"
              value={password}
              onChangeText={setPassword}
              secureTextEntry={!showPassword}
              right={<TextInput.Icon icon={showPassword ? "eye-off" : "eye"} onPress={() => setShowPassword(!showPassword)} />}
              style={styles.input}
            />

            <View style={styles.row}>
              <Checkbox status={agree ? "checked" : "unchecked"} onPress={() => setAgree(!agree)} />
              <Text style={{ color: "#8A8A8A", fontSize: 14, fontWeight: "400" }}>
                I Agree with <Text style={styles.link}>Terms and Conditions</Text>
              </Text>
            </View>

            {loading ? (
              <ActivityIndicator animating size="large" style={{ marginVertical: 12 }} />
            ) : (
              <GradientButton title="Sign Up" onPress={handleSignUp} disabled={!agree} />
            )}

            <Text style={styles.or}>— Or —</Text>
            <GoogleAuthButton onPress={() => console.log("Google sign up")} />

            <View style={styles.footer}>
              <Text>Have an account? </Text>
              <TouchableOpacity onPress={() => navigation.navigate("LogIn")}>
                <Text style={styles.login}>Login</Text>
              </TouchableOpacity>
            </View>
          </View>
        </View>
      </ImageBackground>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: "flex-end" },
  bottomContainer: { flex: 1, backgroundColor: "#EDEDED", borderTopLeftRadius: 30, borderTopRightRadius: 30, position: "absolute", bottom: 0, width: "100%", paddingBottom: 30 },
  banner: { flex: 1, justifyContent: "center", alignItems: "center" },
  formContainer: { flex: 1, justifyContent: "flex-end", padding: 20, paddingBottom: 40 },
  title: { fontSize: 22, fontWeight: "700", color: "#3B3B3B", marginBottom: 10, marginTop: 30 },
  subtitle: { fontSize: 14, fontWeight: "400", color: "#3B3B3B", marginBottom: 12 },
  input: { marginBottom: 16 },
  row: { flexDirection: "row", alignItems: "center", marginBottom: 20 },
  link: { fontSize: 14, color: "#626262", fontWeight: "400", textDecorationLine: "underline" },
  or: { textAlign: "center", marginVertical: 10, color: "#617D8A" },
  footer: { flexDirection: "row", justifyContent: "center" },
  login: { color: "#212A5A", fontWeight: "700" },
});
