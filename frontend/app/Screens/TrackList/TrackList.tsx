import React, { useEffect, useState } from "react";
import {
  FlatList,
  Image,
  ListRenderItem,
  SafeAreaView,
  Text,
  TouchableOpacity,
  View,
} from "react-native";
import TrackPlayer, { Track } from "react-native-track-player";

import { tracks } from "../../../assets/tracts";
import scaling from "../../services/scaling";
import AppPlayer from "../../services/AppPlayer";
import AudioPlayer from "../AudioPlayer/AudioPlayer";
import styles from "./styles";

const { verticalScale } = scaling;

const TracksList = () => {
  const {
    container,
    itemStyle,
    listBox,
    playerBox,
    subTitle,
    subTitleBox,
    title,
    titleBox,
    trackImg,
    trackImgBox,
    trackDescBox,
  } = styles;

  // state vars
  const [selectedTrack, setSelectedTrack] = useState<Track | null>(null);

  useEffect(() => {
    AppPlayer.initializePlayer();
  }, []);

  const onTrackItemPress = async (track: Track) => {
    await TrackPlayer.pause();
    await TrackPlayer.reset();
    setSelectedTrack(track);
  };

  const playNextPrev = async (prevOrNext: "prev" | "next") => {
    const currentTrackId = await TrackPlayer.getCurrentTrack();
    if (!currentTrackId) return;
    const trkIndex = tracks.findIndex((trk) => trk.id === currentTrackId);

    if (prevOrNext === "next" && trkIndex < tracks.length - 1) {
      onTrackItemPress(tracks[trkIndex + 1]);
    }
    if (prevOrNext === "prev" && trkIndex > 0) {
      onTrackItemPress(tracks[trkIndex - 1]);
    }
  };

  const renderItem: ListRenderItem<Track> = ({ item }) => {
    const artImg =
      item.artwork || `https://picsum.photos/150/200/?random=${Math.random()}`;

    let highlightStyle = {};
    if (selectedTrack && selectedTrack.id === item.id)
      highlightStyle = { backgroundColor: "#d1d1e0" };

    return (
      <TouchableOpacity
        onPress={() => onTrackItemPress(item)}
        style={[itemStyle, highlightStyle]}
      >
        <View style={trackImgBox}>
          <Image style={trackImg} source={{ uri: artImg }} />
        </View>
        <View style={trackDescBox}>
          <View style={titleBox}>
            <Text style={title}>{item.title}</Text>
          </View>
          <View style={subTitleBox}>
            <Text style={subTitle}>
              {item.artist || item.album || "Unknown"}
            </Text>
          </View>
        </View>
      </TouchableOpacity>
    );
  };

  let listBoxStyle = {};
  if (selectedTrack) listBoxStyle = { paddingBottom: verticalScale(280) };
  return (
    <SafeAreaView style={container}>
      <View style={[listBox, listBoxStyle]}>
        <FlatList
          data={tracks}
          renderItem={renderItem}
          keyExtractor={(item) => item.id}
        />
      </View>
      {selectedTrack && (
        <View style={playerBox}>
          <AudioPlayer track={selectedTrack} onNextPrevPress={playNextPrev} />
        </View>
      )}
    </SafeAreaView>
  );
};

export default TracksList;
