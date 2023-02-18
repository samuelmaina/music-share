import { Reducer } from "react";
import {
  createStore,
  applyMiddleware,
  combineReducers,
  Action,
  Store,
} from "redux";

import thunk from "redux-thunk";
import authReducer from "./auth";

const rootReducer = combineReducers({
  auth: authReducer,
});

const store: Store = createStore(rootReducer, applyMiddleware(thunk));

export default store;
