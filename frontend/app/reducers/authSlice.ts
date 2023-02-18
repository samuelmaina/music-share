import { createSlice, Slice } from "@reduxjs/toolkit";
import { registerUser } from "../actions/auth";
import { AuthState } from "./types";

const initialState: AuthState = {
  isLoading: false,
  isAuth: false,
  userInfo: {},
  userToken: null,
  error: null,
};

const authSlice: Slice = createSlice({
  name: "auth",
  initialState,
  reducers: {},

  //@ts-ignore
  extraReducers: {
    // register user
    [registerUser.pending]: (state: AuthState) => {
      state.isLoading = true;
      state.error = null;
    },
    [registerUser.fulfilled]: (state: AuthState, { payload }) => {
      state.isLoading = false;
    },
    [registerUser.rejected]: (state: AuthState, { payload }) => {
      state.isLoading = false;
      state.error = payload;
    },
  },
});

export default authSlice.reducer;
