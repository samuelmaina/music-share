import React, { useEffect, useState } from "react";
import { Image, Text, TouchableOpacity, View } from "react-native";
import TrackPlayer, { Track, useProgress } from "react-native-track-player";

import Slider from "@react-native-community/slider";
import Icon from "react-native-vector-icons/Fontisto";
import AppPlayer from "../../services/AppPlayer";
import styles from "./styles";

type compProps = {
  track: Track;
  onNextPrevPress: (p: "prev" | "next") => void;
};

const AudioPlayer = ({ track, onNextPrevPress }: compProps) => {
  const {
    playerMaxView,
    topSection,
    buttonsSection,
    progrsBarSection,
    buttonsCol,
    playPauseButton,
    playPauseIcon,
    trackArtBox,
    trackArt,
    trackDesc,
    trackTitle,
    trackSubtitle,
  } = styles;

  const progress = useProgress();
  const [isPlaying, setPlaying] = useState(true);

  useEffect(() => {
    setPlaying(true);
    TrackPlayer.add(track);
    TrackPlayer.play();
  }, [track]);

  const onPlayPausePress = async () => {
    const state = await TrackPlayer.getState();

    if (state === 2) {
      TrackPlayer.pause();
      setPlaying(false);
    }

    if (state === 3) {
      TrackPlayer.play();
      setPlaying(true);
    }
  };

  const artImg =
    track.artwork || `https://picsum.photos/150/200/?random=${Math.random()}`;

  const playOrPauseIcon = isPlaying ? "pause" : "play";
  return (
    <View style={playerMaxView}>
      <View style={topSection}>
        <View style={trackArtBox}>
          <Image style={trackArt} source={{ uri: artImg }} />
        </View>
        <View style={trackDesc}>
          <View>
            <Text style={trackTitle}> {track.title}</Text>
          </View>
          <View>
            <Text style={trackSubtitle}>
              {track.artist || track.album || "unknown"}
            </Text>
          </View>
        </View>
      </View>
      <View style={progrsBarSection}>
        <Text>
          {AppPlayer.secondsToHHMMSS(Math.floor(progress.position || 0))}
        </Text>
        <Slider
          style={{ width: "70%", height: 40 }}
          minimumValue={0}
          maximumValue={track.duration}
          minimumTrackTintColor="#52527a"
          maximumTrackTintColor="#52527a"
          thumbTintColor="#52527a"
          value={progress.position}
        />
        <Text>{AppPlayer.secondsToHHMMSS(track.duration || 0)}</Text>
      </View>
      <View style={buttonsSection}>
        <View style={[buttonsCol, { alignItems: "flex-end" }]}>
          <TouchableOpacity onPress={() => onNextPrevPress("prev")}>
            <Icon name="step-backwrad" size={18} color="#52527a" />
          </TouchableOpacity>
        </View>
        <View style={buttonsCol}>
          <TouchableOpacity onPress={onPlayPausePress} style={playPauseButton}>
            <Icon
              name={playOrPauseIcon}
              size={14}
              color="#000"
              style={playPauseIcon}
            />
          </TouchableOpacity>
        </View>
        <View style={[buttonsCol, { alignItems: "flex-start" }]}>
          <TouchableOpacity onPress={() => onNextPrevPress("next")}>
            <Icon name="step-forward" size={18} color="#52527a" />
          </TouchableOpacity>
        </View>
      </View>
    </View>
  );
};

export default AudioPlayer;
