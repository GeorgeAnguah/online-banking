import React, { useState } from "react";
import Auth, { initialAuthState } from "../types/Auth"
import User from "../types/User";

export const AuthContext = React.createContext<Auth>(initialAuthState);

export const AuthContextProvider: React.FC = (props) => {
    const [auth, setAuth] = useState<Auth>(initialAuthState);

    const userIsLoggedIn = auth && auth.user && !!auth.user.accessToken; // a truthy trick to check if it's present

    const loginHandler = (user: User) => {
        if (user) {
            setAuth({ ...auth, user, isLoggedIn: true });
        }
    }

    const logoutHandler = () => {
        setAuth({ ...initialAuthState });
    }

    const refreshHandler = (accessToken: string) => {
        setAuth((prevState) => {
            return {
                ...prevState,
                user: {
                    ...prevState.user,
                    accessToken,
                }
            };
        });
    }

    const value = {
        ...auth,
        isLoggedIn: userIsLoggedIn,
        login: loginHandler,
        logout: logoutHandler,
        refresh: refreshHandler
    };
    return (
            <AuthContext.Provider value={value}>
                {props.children}
            </AuthContext.Provider>
    );
}

