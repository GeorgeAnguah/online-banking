import { createAsyncThunk, createSlice, isAnyOf, PayloadAction } from "@reduxjs/toolkit";
import axiosInstance from "../axiosInstance";
import Auth, { initialAuthState } from "../../types/Auth";
import LoginRequest from "../../types/LoginRequest";
import User from "../../types/User";
import AuthState from "../../enums/AuthState";
import { AppState } from "../store";

export const login = createAsyncThunk("auth/login", async (credentials: LoginRequest, thunkAPI) => {
    try {
        const response = await axiosInstance.post<User>("api/v1/auth/login", credentials);

        return response.data;
    } catch (error) {
        return thunkAPI.rejectWithValue({ error: error.message });
    }
});

export const refreshToken = createAsyncThunk("auth/refreshToken", async (_, thunkAPI) => {
    try {
        const response = await axiosInstance.get<User>("api/v1/auth/refresh-token");

        return response.data;
    } catch (error) {
        return thunkAPI.rejectWithValue({ error: error.message });
    }
});

export const logout = createAsyncThunk("auth/logout", async (_, thunkAPI) => {
    try {
        const response = await axiosInstance.delete<{ accessToken: string }>("api/v1/auth/logout");

        return response.data;
    } catch (error) {
        return thunkAPI.rejectWithValue({ error: error.message });
    }
});

export const authSlice = createSlice({
    name: "auth",
    initialState: initialAuthState,
    reducers: {
        updateAuthState(state: Auth, action: PayloadAction<{ user: User }>) {
            state.user = action.payload.user;
        },
        reset: () => initialAuthState,
    },
    extraReducers: (builder) => {
        builder.addCase(logout.pending, (state) => {
            state.loading = AuthState.LOADING;
        });
        builder.addCase(logout.fulfilled, (_state) => initialAuthState);
        builder.addMatcher(isAnyOf(login.pending, refreshToken.pending), (state) => {
            state.loading = AuthState.LOADING;
        });
        builder.addMatcher(
            isAnyOf(login.fulfilled, refreshToken.fulfilled),
            (state: Auth, action: PayloadAction<User>) => {
                state.user = action.payload;
                state.isLoggedIn = !!action.payload.accessToken;
                state.loading = AuthState.IDLE;
            }
        );
        builder.addMatcher(isAnyOf(login.rejected, refreshToken.rejected), (state, action) => {
            Object.assign(state, { ...initialAuthState, loading: AuthState.FAILED, error: action.error });
        });
    },
});

// The function below is called a selector and allows us to select a value from
// the state. Selectors can also be defined inline where they're used instead of
// in the slice file. For example: `useSelector((state: RootState) => state.counter.value)`
export const selectAuth = (state: AppState) => state.authReducer;

export const { updateAuthState, reset } = authSlice.actions;
