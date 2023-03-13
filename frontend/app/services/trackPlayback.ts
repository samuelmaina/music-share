// src/services/PlaybackService.ts
import TrackPlayer, { Event } from "react-native-track-player";

export default async function () {
  TrackPlayer.addEventListener(Event.RemotePlay, () => TrackPlayer.play());

  TrackPlayer.addEventListener(Event.RemotePause, () => TrackPlayer.pause());

  // ...
}
