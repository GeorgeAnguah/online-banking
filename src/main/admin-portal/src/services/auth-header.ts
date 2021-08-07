import { useState } from 'react';
import User from '../types/User';

const AuthHeader = () => {

    const [user] = useState<User>();

    if (user && user.accessToken) {
        return { Authorization: `Bearer ${user.accessToken}` }
    }

    return {};
}

export default AuthHeader;