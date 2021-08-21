type LoginRequest = {
    username: string;
    password: string;
}

export const initialUserState: LoginRequest = {
    username: '',
    password: '',
};

export default LoginRequest;