import { configureStore } from "@reduxjs/toolkit";
import { ToolkitStore } from "@reduxjs/toolkit/dist/configureStore";
import authReducer from "./authSlice";

const store: ToolkitStore = configureStore({
  reducer: {
    auth: authReducer,
  },
});
export default store;
