const AuthHeader = (accessToken: string) => {

    if (accessToken) {
        return { Authorization: `Bearer ${accessToken}`}
    }

    return {};
}

export default AuthHeader;