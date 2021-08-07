import axios from 'axios';
import { useState } from 'react';
import User from "../types/User";

const API_AUTH_URL = "http://localhost:8080/api/auth";

/**
 * Posts username and password to the auth service endpoint.
 *
 * @param {username} username
 * @param {password} password
 */
const login = async (username: string, password: string) => {
    const [user, setUser] = useState<User>();

    let axiosResponse = await axios.post(`${API_AUTH_URL}/login`, { username, password });
    if (axiosResponse && axiosResponse.data.accessToken) {
        console.log("user: ", axiosResponse.data)

        setUser(axiosResponse.data);
        console.log("Current state of user is ", user);
    }
}

const AuthService = { login };
export default AuthService;