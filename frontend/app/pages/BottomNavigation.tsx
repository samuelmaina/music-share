import React from "react";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";

import { Home, Login, SignUp } from ".";
import { useSelector } from "react-redux";
import TracksList from "../Screens/TrackList/TrackList";

const Tab = createBottomTabNavigator();

const BottomNavigation: React.FC = (): JSX.Element => {
  const { isAuth } = useSelector((state: any) => state.auth);
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
      {!isAuth && (
        <Tab.Screen
          name="PlayList"
          component={TracksList}
          options={{
            tabBarLabel: "TrackList",
            tabBarIcon: ({ color, size }) => (
              <MaterialCommunityIcons
                name="music-circle"
                color={color}
                size={size}
              />
            ),
          }}
        />
      )}
      {!isAuth && (
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
      )}
      {!isAuth && (
        <Tab.Screen
          name="Login"
          component={Login}
          options={{
            tabBarLabel: "Log In",
            tabBarIcon: ({ color, size }) => (
              <MaterialCommunityIcons name="login" color={color} size={size} />
            ),
          }}
        />
      )}
      {isAuth && (
        <Tab.Screen
          name="Logout"
          component={Home}
          options={{
            tabBarIcon: ({ color, size }) => (
              <MaterialCommunityIcons name="logout" color={color} size={size} />
            ),
          }}
        />
      )}
    </Tab.Navigator>
  );
};
export default BottomNavigation;
