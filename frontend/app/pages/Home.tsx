import React, { useRef, useEffect } from "react";
import { Animated, Text, View } from "react-native";
import type { PropsWithChildren } from "react";
import type { ViewStyle } from "react-native";

type FadeInViewProps = PropsWithChildren<{ style: ViewStyle }>;

const FadeInView: React.FC<FadeInViewProps> = (props) => {
  const fadeAnim = useRef(new Animated.Value(0)).current; // Initial value for opacity: 0

  useEffect(() => {
    Animated.timing(fadeAnim, {
      toValue: 1,
      duration: 10000,
      useNativeDriver: true,
    }).start();
  }, [fadeAnim]);

  return (
    <Animated.View // Special animatable View
      style={{
        ...props.style,
        opacity: fadeAnim, // Bind opacity to animated value
      }}
    >
      {props.children}
    </Animated.View>
  );
};

// You can then use your `FadeInView` in place of a `View` in your components:
export default () => {
  return (
    <View
      style={{
        flex: 1,
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      <FadeInView
        style={{
          width: 250,
          height: 50,
          backgroundColor: "powderblue",
        }}
      >
        <Text style={{ fontSize: 28, textAlign: "center", margin: 10 }}>
          Fading in
        </Text>
      </FadeInView>
    </View>
  );
};

// import { StatusBar } from "expo-status-bar";
// import { StyleSheet, Text, View } from "react-native";

// export default function Home() {
//   return (
//     <View style={styles.container}>
//       <Text>Open up Home.tsx to start working on your ap</Text>
//       <Text>The test would be good to be there</Text>
//       <StatusBar style="auto" />
//     </View>
//   );
// }

// const styles = StyleSheet.create({
//   container: {
//     flex: 1,
//     backgroundColor: "#fff",
//     alignItems: "center",
//     justifyContent: "center",
//   },
// });
