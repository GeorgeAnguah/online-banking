import React, { useEffect } from "react";
import MainNavigation from "./MainNavigation";
import classes from "./Layout.module.css";
import { AppDispatch } from "../../app/store";
import { useAppDispatch } from "../../app/hooks";
import { refreshToken } from "../../app/slices/auth";

const Layout: React.FC = ({ children }) => {
    const dispatch: AppDispatch = useAppDispatch();

    useEffect(() => {
        dispatch(refreshToken());
    });

    return (
        <div>
            <MainNavigation />
            <main className={classes.main}>{children}</main>
        </div>
    );
};

export default Layout;
