import axios from 'axios';

const API_AUTH_URL = "http://localhost:8080/api/auth";

/**
 * Posts username and password to the auth service endpoint.
 *
 * @param {username} username
 * @param {password} password
 */
const login = async (username: string, password: string) => {

    let axiosResponse = await axios.post(`${API_AUTH_URL}/login`, { username, password }, { withCredentials: true });
    if (axiosResponse && axiosResponse.data.accessToken) {
        console.log("response => ", axiosResponse.headers.cookie)
        return axiosResponse.data;
    }
}

const refresh = async () => {
    let axiosResponse = await axios.post(`${API_AUTH_URL}/refresh`, {}, { withCredentials: true });
    if (axiosResponse && axiosResponse.data) {
        console.log("response => ", axiosResponse.data)
        return axiosResponse.data;
    }
}

const logout = async () => {
    let axiosResponse = await axios.post(`${API_AUTH_URL}/logout`);
    return axiosResponse.data;
}

const AuthService = { login, refresh, logout };
export default AuthService;