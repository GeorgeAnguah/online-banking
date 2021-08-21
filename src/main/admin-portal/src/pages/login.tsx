import React from 'react';
import LoginForm from '../features/login/LoginForm';
import { useAppDispatch } from '../app/hooks';
import { AppDispatch } from '../app/store';
import LoginRequest from '../types/LoginRequest';
import { login } from '../app/slices/auth';
import { useRouter } from 'next/router';

const Login: React.FC = () => {
    const router = useRouter();
    const dispatch: AppDispatch = useAppDispatch();

    const submitHandler = async (credentials: LoginRequest) => {
        await dispatch(login(credentials));
        await router.push('/');
    }

    return (
            <LoginForm onSubmit={submitHandler} />
    );
}

export default Login;