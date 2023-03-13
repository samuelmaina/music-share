import React from "react";
import { SafeAreaView, StatusBar, Text, View } from "react-native";
import TracksList from "../../Screens/TrackList/TrackList";
import styles from "./styles";

const AudioPage = () => {
  const { appContainer, content, header, headerTitle } = styles;

  return (
    <SafeAreaView style={appContainer}>
      <StatusBar backgroundColor={"#35427e"} />
      <View style={header}>
        <Text style={headerTitle}>Audio Player</Text>
      </View>
      <View style={content}>
        <TracksList />
      </View>
    </SafeAreaView>
  );
};

export default AudioPage;
