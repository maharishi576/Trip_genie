// src/config/api.ts
import { Platform } from "react-native";

const LOCALHOST = Platform.OS === "android" ? "http://10.0.2.2:8080" : "http://localhost:8080";
// NOTE: If you run on a real device, replace LOCALHOST with `http://192.168.x.y:8080`
export const API_BASE = process.env.API_BASE_URL || LOCALHOST;

async function post(path: string, body: any) {
  const res = await fetch(`${API_BASE}${path}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body),
  });

  const json = await res.json().catch(() => ({}));
  if (!res.ok) {
    throw new Error(json?.message || `HTTP ${res.status}`);
  }
  return json;
}

export function requestOtp(payload: { email: string; name: string; mobile?: string }) {
  return post("/api/auth/signup/request-otp", payload);
}

export function verifyOtp(payload: { email: string; otp: string; name?: string; mobile?: string }) {
  return post("/api/auth/signup/verify-otp", payload);
}
