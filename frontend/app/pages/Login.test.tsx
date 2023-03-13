import React from "react";
import { render, fireEvent } from "@testing-library/react-native";
import Login from "./Login";

describe("Login page", () => {
  it("should display email and password input fields", () => {
    const mockLoginHandler = jest.fn();
    const { getByTestId } = render(<Login loginHandler={mockLoginHandler} />);
    const emailInput = getByTestId("email-input");
    const passwordInput = getByTestId("password-input");

    expect(emailInput).toBeTruthy();
    expect(passwordInput).toBeTruthy();
  });

  it("should update email and password state when typing in inputs", () => {
    const mockLoginHandler = jest.fn();
    const { getByTestId } = render(<Login loginHandler={mockLoginHandler} />);
    const emailInput = getByTestId("email-input");
    const passwordInput = getByTestId("password-input");

    fireEvent.changeText(emailInput, "test@test.com");
    fireEvent.changeText(passwordInput, "test1234");

    expect(emailInput.props.value).toEqual("test@test.com");
    expect(passwordInput.props.value).toEqual("test1234");
  });

  it("should call loginHandler with email and password when pressing login button", () => {
    const mockLoginHandler = jest.fn();
    const { getByTestId } = render(<Login loginHandler={mockLoginHandler} />);
    const emailInput = getByTestId("email-input");
    const passwordInput = getByTestId("password-input");
    const loginButton = getByTestId("login-button");

    fireEvent.changeText(emailInput, "test@test.com");
    fireEvent.changeText(passwordInput, "test1234");
    fireEvent.press(loginButton);

    expect(mockLoginHandler).toHaveBeenCalledTimes(1);
    expect(mockLoginHandler).toHaveBeenCalledWith({
      email: "test@test.com",
      password: "test1234",
    });
  });
});
