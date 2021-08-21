import React from "react";
import { useAppSelector } from "../../app/hooks";
import AuthState from "../../enums/AuthState";
import { selectAuth } from "../../app/slices/auth";
import Login from "../../pages/login";

type Props = {
    readonly role?: "ROLE_ADMIN";
    readonly customText?: React.ReactNode;
};

export const AuthGuard: React.FC<Props> = ({ children, customText }) => {
    const { loading, user } = useAppSelector(selectAuth);

    if (loading === AuthState.LOADING) {
        return <>loading...</>;
    }

    if (user && user.roles && user.roles.includes("ROLE_ADMIN")) {
        return <>{children}</>;
    }

    return customText ? (
        <section>
            <h2 className="text-center">Unauthorized</h2>
            <div className="text-center">
                {customText || "You don't have permission to access this page. Please contact an admin."}
            </div>
        </section>
    ) : (
        <Login />
    );
};
