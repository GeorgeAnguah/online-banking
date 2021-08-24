import { Action, AnyAction, combineReducers, configureStore, ThunkAction } from "@reduxjs/toolkit";
import { HYDRATE } from "next-redux-wrapper";
import { authSlice } from "./slices/auth";

const combinedReducers = combineReducers({
    authReducer: authSlice.reducer,
});
export type OurStore = ReturnType<typeof combinedReducers>;

const rootReducer = (state: ReturnType<typeof combinedReducers>, action: AnyAction) => {
    if (action.type === HYDRATE) {
        return {
            ...state,
            ...action.payload,
        };
    }
    return combinedReducers(state, action);
};

const store = configureStore<OurStore>({
    reducer: rootReducer,
});

export type AppState = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;

export type AppThunk<ReturnType = void> = ThunkAction<ReturnType, AppState, unknown, Action<string>>;

export default store;
