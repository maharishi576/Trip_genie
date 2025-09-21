// App.tsx
import { NavigationContainer } from "@react-navigation/native";
import React from "react";
import { GestureHandlerRootView } from "react-native-gesture-handler";
import { SafeAreaView } from "react-native-safe-area-context";
import "../src/navigation/RootStack";
import RootStack from "../src/navigation/RootStack";
// instead of import { SafeAreaView } from 'react-native'
import { Provider as PaperProvider, DefaultTheme, MD3LightTheme} from 'react-native-paper';

export default function App() {
  const theme = {
  ...MD3LightTheme,
  colors: {
    ...MD3LightTheme.colors,
    primary: "#1F6F9A",   // your main brand color
    secondary: "#1F6F9A", // optional, for accents
  },
};

  return (
    <GestureHandlerRootView style={{ flex: 1 }}>
      <SafeAreaView>
        <PaperProvider theme={theme}>
          <NavigationContainer>
            <RootStack />
          </NavigationContainer>
        </PaperProvider>
      </SafeAreaView>
    </GestureHandlerRootView>
  );
}
