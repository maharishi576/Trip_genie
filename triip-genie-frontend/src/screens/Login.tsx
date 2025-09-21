// src/screens/Login.tsx
import { useNavigation } from "@react-navigation/native";
import type { NativeStackNavigationProp } from "@react-navigation/native-stack";
import { useRouter } from "expo-router";
import React, { useState } from "react";
import {
  Alert,
  ImageBackground,
  StyleSheet,
  TouchableOpacity,
  View,
} from "react-native";
import { Checkbox, Text, TextInput } from "react-native-paper";
import "../components/GoogleAuthButton";
import GoogleAuthButton from "../components/GoogleAuthButton";
import GradientButton from "../components/GradientButton";
import type { RootStackParamList } from "../types/navigation";

type NavProp = NativeStackNavigationProp<RootStackParamList, "LogIn">;

export default function Login() {
  const navigation = useNavigation<NavProp>();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [remember, setRemember] = useState(false);
  const [showPassword, setShowPassword] = useState(false);

  const router = useRouter();

  const onSignIn = () => {
    navigation.replace("Home");
  };
  return (
    <View style={styles.container}>
      <ImageBackground
        source={require("../../assets/images/LoginBanner.png")}
        style={styles.banner}
        resizeMode="cover"
      >
        <View style={styles.bottomContainer}>
          <View style={styles.formContainer}>
            <Text variant="titleLarge" style={styles.title}>
              Login
            </Text>
            <Text style={styles.subtitle}>Please Login to Your Account</Text>

            <TextInput
              mode="outlined"
              label="Email/Mobile"
              value={email}
              onChangeText={setEmail}
              style={styles.input}
            />
            <TextInput
              mode="outlined"
              label="Password"
              value={password}
              onChangeText={setPassword}
              secureTextEntry={!showPassword}
              right={
                <TextInput.Icon
                  icon={showPassword ? "eye-off" : "eye"}
                  onPress={() => setShowPassword(!showPassword)}
                />
              }
              style={styles.input}
            />

            <View style={styles.row}>
              <View style={styles.rememberRow}>
                <Checkbox
                  status={remember ? "checked" : "unchecked"}
                  onPress={() => setRemember(!remember)}
                />
                <Text>Remember Me</Text>
              </View>
              <TouchableOpacity>
                <Text style={styles.forgot}>Forgot Password?</Text>
              </TouchableOpacity>
            </View>

            <GradientButton
              title="Login"
              onPress={onSignIn} // 👈 use "MainPage"
            />
            

            <Text style={styles.or}>— Or —</Text>

            <GoogleAuthButton onPress={() => Alert.alert("Google auth")} />

            <View style={styles.footer}>
              <Text>Don’t have an account? </Text>
              <TouchableOpacity onPress={() => navigation.navigate("Signup")}>
                <Text style={styles.signup}>Sign up</Text>
              </TouchableOpacity>
            </View>
          </View>
        </View>
      </ImageBackground>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: "#EDEDED" },
  banner: { flex: 1, justifyContent: "center", alignItems: "center" },
  bottomContainer: {
    flex: 1,
    backgroundColor: "#EDEDED",
    borderTopLeftRadius: 30,
    borderTopRightRadius: 30,
    position: "absolute",
    bottom: 0,
    width: "100%",
    paddingBottom: 30,
  },
  formContainer: {
    flex: 1,
    justifyContent: "flex-end",
    padding: 20,
    paddingBottom: 40,
  },
  title: { fontWeight: "700", marginBottom: 4, marginTop: 30 },
  subtitle: { marginBottom: 20, color: "#666" },
  input: { marginBottom: 16 },
  row: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    marginBottom: 20,
  },
  rememberRow: { flexDirection: "row", alignItems: "center" },
  forgot: { color: "#3B82F6" },
  or: { textAlign: "center", marginVertical: 10, color: "#999" },
  footer: { flexDirection: "row", justifyContent: "center" },
  signup: { color: "#212A5A", fontWeight: "700" },
});
