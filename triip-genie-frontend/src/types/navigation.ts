// src/types/navigation.ts
export type RootStackParamList = {
  Splash: undefined;
  MainPage: undefined;
  LogIn: undefined;
  Signup: undefined;
  Home: undefined;
  HomeScreen: undefined;
  OtpVerify: { email: string; name?: string; mobile?: string };
};
