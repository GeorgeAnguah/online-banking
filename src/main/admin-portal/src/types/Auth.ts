import User, {initialUserState} from "./User";
import {SerializedError} from "@reduxjs/toolkit";
import AuthState from "../enums/AuthState";

type Auth = {
    user: User,
    isLoggedIn: boolean,
    loading: AuthState,
    error?: SerializedError
};

export const initialAuthState = {
    loading: AuthState.IDLE,
    error: null,
    me: null,
    user: initialUserState,
    isLoggedIn: false
};

export default Auth;