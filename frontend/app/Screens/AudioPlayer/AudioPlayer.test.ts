import React from "react";
import { render, fireEvent } from "@testing-library/react-native";
import AudioPlayer from "./AudioPlayer";

describe("AudioPlayer Component", () => {
  const track = {
    id: "1",
    title: "Test Track",
    artist: "Test Artist",
    album: "Test Album",
    duration: 300,
  };

  it("renders correctly", () => {
    const { getByText, getByTestId } = render(
      <AudioPlayer track={track} onNextPrevPress={() => {}} />
    );

    // Check if the track title and artist are displayed
    expect(getByText("Test Track")).toBeTruthy();
    expect(getByText("Test Artist")).toBeTruthy();

    // Check if play/pause button is displayed
    expect(getByTestId("play-pause-button")).toBeTruthy();

    // Check if progress bar is displayed
    expect(getByTestId("progress-bar")).toBeTruthy();
  });

  it("toggles play/pause on button press", async () => {
    const { getByTestId } = render(
      <AudioPlayer track={track} onNextPrevPress={() => {}} />
    );

    const playPauseButton = getByTestId("play-pause-button");

    expect(playPauseButton.props.children.props.name).toBe("play");

    fireEvent.press(playPauseButton);
    expect(playPauseButton.props.children.props.name).toBe("pause");
  });

  it("can press the next button", async () => {
    const { getByTestId } = render(
      <AudioPlayer track={track} onNextPrevPress={() => {}} />
    );

    const playPauseButton = getByTestId("play-next-button");
    //the passing of this test is that it does not throw
    expect(playPauseButton.props.children.props.name).toBe("next");
    fireEvent.press(playPauseButton);
  });
  it("can press the prev button", async () => {
    const { getByTestId } = render(
      <AudioPlayer track={track} onNextPrevPress={() => {}} />
    );

    const playPauseButton = getByTestId("play-prev-button");

    expect(playPauseButton.props.children.props.name).toBe("prev");
    //the passing of this test is that it does not throw
    fireEvent.press(playPauseButton);
  });
});
