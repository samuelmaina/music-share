import { LOGIN_REQUEST, LOGIN_SUCCESS, LOGIN_FAILURE } from "../actions";
import { action, state } from "./types";

const initialState: state = {
  isLoading: false,
  isAuth: false,
  user: null,
  error: null,
};

const authReducer = (action: action, state: state = initialState): state => {
  switch (action.type) {
    case LOGIN_REQUEST:
      return {
        ...state,
        isLoading: true,
        isAuth: false,
        user: null,
        error: null,
      };
    case LOGIN_SUCCESS:
      return {
        ...state,
        isLoading: false,
        isAuth: true,
        user: action.payload.user,
        error: null,
      };
    case LOGIN_FAILURE:
      return {
        ...state,
        isLoading: false,
        isAuth: false,
        user: null,
        error: action.payload.error,
      };
    default:
      return state;
  }
};

export default authReducer;
