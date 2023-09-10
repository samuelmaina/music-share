import React from "react";
import { render, fireEvent } from "@testing-library/react-native";
import TracksList from "./TracksList";

describe("TracksList Component", () => {
  it("renders correctly", () => {
    const { getByText } = render(<TracksList />);

    // Check if the component renders without errors
    expect(getByText("Track Title 1")).toBeTruthy();
    expect(getByText("Track Title 2")).toBeTruthy();
    // Add more assertions for other elements as needed
  });

  it("selects a track on item press", async () => {
    const { getByText } = render(<TracksList />);

    // Find a track item and click it
    const trackItem = getByText("Track Title 1");
    fireEvent.press(trackItem);

    // Assert that the selected track's title is displayed
    expect(getByText("Track Title 1")).toBeTruthy();

    // You can add more assertions to check if the player is displayed, etc.
  });

  it("plays next track on next button press", async () => {
    const { getByTestId } = render(<TracksList />);

    // Find the next button and click it
    const nextButton = getByTestId("next-button");
    fireEvent.press(nextButton);

    // Assert that the next track is selected and displayed
    expect(getByTestId("player-title")).toHaveTextContent("Track Title 2");
  });

  it("plays previous track on previous button press", async () => {
    const { getByTestId } = render(<TracksList />);

    // Select the second track
    const trackItem = getByText("Track Title 2");
    fireEvent.press(trackItem);

    // Find the previous button and click it
    const prevButton = getByTestId("prev-button");
    fireEvent.press(prevButton);

    // Assert that the previous track is selected and displayed
    expect(getByTestId("player-title")).toHaveTextContent("Track Title 1");
  });

  // Add more tests for various scenarios and edge cases
});
