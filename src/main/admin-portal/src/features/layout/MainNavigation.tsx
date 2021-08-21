import React from "react";
import { Container, Nav, Navbar } from "react-bootstrap";
import { useAppDispatch, useAppSelector } from "../../app/hooks";
import Auth from "../../types/Auth";
import { logout, selectAuth } from "../../app/slices/auth";
import { AppDispatch } from "../../app/store";
import { useRouter } from "next/router";

const MainNavigation: React.FC = (props) => {
    const router = useRouter();
    const auth = useAppSelector<Auth>(selectAuth);
    const dispatch: AppDispatch = useAppDispatch();

    const logoutHandler = async (event: React.MouseEvent) => {
        event.preventDefault();

        await dispatch(logout());
        await router.push("/login");
    };

    return (
        <Navbar bg="light" expand="lg">
            <Container>
                <Navbar.Brand href="#home">Admin Portal</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                {auth.isLoggedIn && (
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link href="#home">User Account</Nav.Link>
                            <Nav.Link href="#link">Appointment</Nav.Link>
                        </Nav>
                        <Nav>
                            <Nav.Link onClick={logoutHandler}>Logout</Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
                )}
            </Container>
        </Navbar>
    );
};

export default MainNavigation;
