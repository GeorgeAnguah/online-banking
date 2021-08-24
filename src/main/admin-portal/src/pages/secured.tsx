import React from "react";
import dynamic from "next/dynamic";

// Dynamically import the AuthGuard component.
const AuthGuard = dynamic<{ readonly customText: React.ReactNode }>(() =>
    import("../features/layout/AuthGuard").then((mod) => mod.AuthGuard)
);

const Secured: React.FC = () => {
    return (
        <AuthGuard
            // No custom message as we are defaulting to login page.
            customText={undefined}
        >
            <h2>Welcome ADMIN, You are in a secured spot!</h2>
        </AuthGuard>
    );
};

export default Secured;
