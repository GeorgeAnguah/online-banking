type User = {
    accessToken: string;
    publicId: string;
    username: string;
    email: string;
    roles: string[];
}

export const initialUserState: User = {
    accessToken: '',
    publicId: '',
    username: '',
    email: '',
    roles: [],
};

export default User;