import "react-native-gesture-handler";

import React from "react";
import { NavigationContainer } from "@react-navigation/native";
import { Provider } from "react-redux";
import store from "./app/reducers/store";
import { BottomNavigation } from "./app/components";

const App = () => {
  return (
    <NavigationContainer>
      <Provider store={store}>
        <BottomNavigation />
      </Provider>
    </NavigationContainer>
  );
};

export default App;
