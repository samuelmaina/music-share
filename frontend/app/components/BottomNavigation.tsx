import React from "react";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";

import { Home, Login, SignUp } from "../pages";

const Tab = createBottomTabNavigator();

const BottomNavigation: React.FC = (): JSX.Element => {
  return (
    <Tab.Navigator>
      <Tab.Screen
        name="Home"
        component={Home}
        options={{
          tabBarLabel: "Home",
          tabBarIcon: ({ color, size }) => (
            <MaterialCommunityIcons name="home" color={color} size={size} />
          ),
        }}
      />
      <Tab.Screen
        name="Sign UP"
        component={SignUp}
        options={{
          tabBarLabel: "Sign UP",
          tabBarIcon: ({ color, size }) => (
            <MaterialCommunityIcons
              name="sign-direction-plus"
              color={color}
              size={size}
            />
          ),
        }}
      />
      <Tab.Screen
        name="Login"
        component={Login}
        options={{
          tabBarLabel: "Login",
          tabBarIcon: ({ color, size }) => (
            <MaterialCommunityIcons name="login" color={color} size={size} />
          ),
        }}
      />
    </Tab.Navigator>
  );
};
export default BottomNavigation;
