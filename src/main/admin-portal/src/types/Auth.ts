import User, { initialUserState } from "./User";

type Auth = {
    user: User,
    isLoggedIn: boolean,
    login: Function,
    logout: Function,
    refresh: Function
};

export const initialAuthState = {
    user: initialUserState,
    isLoggedIn: false,
    login: Function,
    logout: Function,
    refresh: Function
};

export default Auth;