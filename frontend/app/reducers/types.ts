export type state = {
  isLoading: boolean;
  isAuth: boolean;
  user: any;
  error: string | null;
};

export type action = {
  type: string;
  payload: any;
};
