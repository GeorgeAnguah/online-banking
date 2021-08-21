import React, { useRef } from "react";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import LoginRequest from "../../types/LoginRequest";

const LoginForm: React.FC<{ onSubmit }> = (props) => {
    const usernameInputRef = useRef(null);
    const passwordInputRef = useRef(null);

    const submitHandler = (event: React.FormEvent) => {
        event.preventDefault();

        const enteredUsername = usernameInputRef.current.value;
        const enteredPassword = passwordInputRef.current.value;

        const loginRequest: LoginRequest = {
            username: enteredUsername,
            password: enteredPassword,
        };
        props.onSubmit(loginRequest);
    };

    return (
        <Container>
            <Row>
                <Col lg={{ span: 4, offset: 4 }} className="mt-4">
                    <Form onSubmit={submitHandler}>
                        <fieldset className="border p-5">
                            <legend className="w-auto me-auto">Please login</legend>

                            <Form.Group className="mb-3" controlId="username">
                                <Form.Control type="text" placeholder="Username" ref={usernameInputRef} />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="password">
                                <Form.Control type="password" placeholder="Password" ref={passwordInputRef} />
                            </Form.Group>
                            <div className="mb-3 d-grid gap-2 mx-auto">
                                <Button variant="primary" type="submit">
                                    Login
                                </Button>
                            </div>
                        </fieldset>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
};

export default LoginForm;
