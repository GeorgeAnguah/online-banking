import React, {useContext, useEffect} from 'react';
import logo from './logo.svg';
import "bootstrap/dist/css/bootstrap.min.css";
import './App.css';
import Auth from "./types/Auth";
import {AuthContext} from "./shared/context";
import AuthService from "./services/AuthService";
import {getAdminBoard} from "./services/user.service";

function App() {
    const authCtx = useContext<Auth>(AuthContext);

    useEffect(() => {
        AuthService.login('admin', 'admin')
                .then(response => {
                    authCtx.login(response);
                    getAdminBoard(response.accessToken)
                            .then(data => console.log("from admin board: ", data))
                            .catch(error => console.log(error));
                }).then(() => {
            setTimeout(() => {
                AuthService.refresh()
                        .then(refreshedAccessToken => {
                            authCtx.refresh(refreshedAccessToken);
                        })
                        .catch(error => console.log(error));
            }, 10000);
        });

    }, []);
    return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <p>
                        Edit <code>src/App.tsx</code> and save to reload.
                    </p>
                    <a
                            className="App-link"
                            href="https://reactjs.org"
                            target="_blank"
                            rel="noopener noreferrer"
                    >
                        Learn React
                    </a>
                </header>
            </div>
    );
}

export default App;
