import "react-native-gesture-handler";

import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import { Login, Home } from "./app/pages";
import { NavigationContainer } from "@react-navigation/native";

import { createDrawerNavigator } from "@react-navigation/drawer";

import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";

const Stack = createStackNavigator();
const Drawer = createDrawerNavigator();

const App = () => {
  const Tab = createBottomTabNavigator();
  const d = (
    <Drawer.Navigator initialRouteName="Home">
      <Stack.Screen name="Home" component={Home} />
      <Stack.Screen name="Login" component={Login} />
    </Drawer.Navigator>
  );
  return (
    <NavigationContainer>
      <Tab.Navigator initialRouteName="Home">
        <Tab.Screen name="Home" component={Home} />
        <Tab.Screen name="Login" component={Login} />
      </Tab.Navigator>
      {/* <Stack.Navigator initialRouteName="Login">
        <Stack.Screen name="Login" component={Login} />
        <Stack.Screen name="Home" component={Home} />
      </Stack.Navigator> */}
    </NavigationContainer>
  );
};

export default App;
