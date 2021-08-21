import axios from "axios";
import createAuthRefreshInterceptor from "axios-auth-refresh";
import { reset, updateAuthState } from "./slices/auth";
import store, { AppDispatch } from "./store";
import User from "../types/User";

// Create axios instance.
const axiosInstance = axios.create({
    baseURL: "http://localhost:8080",
    withCredentials: true,
    headers: {
        "Content-Type": "application/json",
    },
});

// Create axios interceptor
createAuthRefreshInterceptor(axiosInstance, (failedRequest) =>
    // 1. First try request fails - refresh the token.
    axiosInstance
        .get<User>("/api/v1/auth/refresh-token")
        .then((resp) => {
            const { dispatch }: { dispatch: AppDispatch } = store; // get dispatch action
            const user = resp.data;
            dispatch(updateAuthState({ user }));

            // 2. Set up new access token
            const bearer = `Bearer ${user.accessToken}`;
            axiosInstance.defaults.headers.Authorization = bearer;

            // 3. Set up access token of the failed request.
            failedRequest.response.config.headers.Authorization = bearer;

            // 4. Retry the request with new setup!
            return Promise.resolve();
        })
        .catch(() => {
            const { dispatch }: { dispatch: AppDispatch } = store; // get dispatch action
            dispatch(reset());
        })
);

export default axiosInstance;
