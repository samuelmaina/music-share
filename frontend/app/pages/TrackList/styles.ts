import { StyleSheet } from "react-native";

const styles = StyleSheet.create({
  appContainer: {
    flex: 1,
    justifyContent: "space-around",
    alignItems: "stretch",
    display: "flex",
    backgroundColor: "black",
  },
  header: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#666699",
  },
  headerTitle: {
    fontSize: 22,
    fontWeight: "bold",
    color: "#d1d1e0",
  },
  content: {
    flex: 8,
    justifyContent: "space-around",
    alignItems: "stretch",
    backgroundColor: "#e0e0eb",
  },
});

export default styles;
