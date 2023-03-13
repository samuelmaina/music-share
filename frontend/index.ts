import { AppRegistry } from "react-native";
import TrackPlayer from "react-native-track-player";
import App from "./App";
import { expo } from "./app.json";
import playbackServices from "./app/services/trackPlayback";
const appName = expo.name;

AppRegistry.registerComponent(appName, () => App);
TrackPlayer.registerPlaybackService(() => playbackServices);
