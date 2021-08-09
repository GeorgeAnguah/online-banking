import axios from 'axios';
import authHeader from './AuthHeader';

const API_URL = "http://localhost:8080/api/test/";

export const getAdminBoard = (accessToken: string) => {
    return axios.get(API_URL + "admin", { headers: authHeader(accessToken) });
}