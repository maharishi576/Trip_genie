// src/navigation/HomeTabs.tsx
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import React from "react";
import { Image } from "react-native";
import HomeScreen from "../screens/HomeScreen";
import ProfileScreen from "../screens/ProfileScreen"; // create placeholder if you don't have it
import TrendingScreen from "../screens/TrendingScreen"; // create placeholder if you don't have it

const Tab = createBottomTabNavigator();

export default function HomeTabs() {
  return (
    <Tab.Navigator
      screenOptions={({ route }) => ({
        headerShown: false,
        tabBarShowLabel: false,
        tabBarIcon: ({ focused }) => {
          let icon;
          if (route.name === "HomeScreen") {
            icon = focused
              ? require("../../assets/images/icons/home-active.png")
              : require("../../assets/images/icons/home.png");
          } else if (route.name === "Trending") {
            icon = focused
              ? require("../../assets/images/icons/trending-active.png")
              : require("../../assets/images/icons/trending.png");
          } else if (route.name === "Profile") {
            icon = focused
              ? require("../../assets/images/icons/profile-active.png")
              : require("../../assets/images/icons/profile.png");
          }
          return <Image source={icon} style={{ width: 26, height: 26 }} />;
        },
        tabBarStyle: { height: 62, borderTopLeftRadius: 16, borderTopRightRadius: 16 },
      })}
    >
      <Tab.Screen name="HomeScreen" component={HomeScreen} />
      <Tab.Screen name="Trending" component={TrendingScreen} />
      <Tab.Screen name="Profile" component={ProfileScreen} />
    </Tab.Navigator>
  );
}
