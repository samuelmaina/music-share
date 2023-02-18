export type AuthState = {
  isLoading: boolean;
  isAuth: boolean;
  userInfo: object;
  userToken: string | null;
  error: string | null;
};

export type Action = {
  type: string;
  payload: any;
};
