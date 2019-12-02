package com.rozsa.business;

public class Login {
    private final String username;

    private final String password;

    private final OnLoginSuccess onSuccess;

    private final OnLoginFailure onFailure;

    private final Object state;

    public Login(String username, String password, OnLoginSuccess onSuccess, OnLoginFailure onFailure, Object state) {
        this.username = username;
        this.password = password;
        this.onSuccess = onSuccess;
        this.onFailure = onFailure;
        this.state = state;
    }

    public void execute() {
        String expectedUsername = System.getProperty("username");
        String expectedPassword = System.getProperty("password");

        if (expectedUsername == null || expectedPassword == null) {
            System.out.println("Expected username or password is not set in VM!");
            onFailure.execute(state);
            return;
        }

        if (expectedUsername.equals(username) && expectedPassword.equals(password)) {
            System.out.println("Authentication success for user " + username);
            onSuccess.execute(state);
            return;
        }

        System.out.println("Authentication failure for user " + username);
        onFailure.execute(state);
    }

    @FunctionalInterface
    public interface OnLoginSuccess {
        void execute(Object state);
    }

    @FunctionalInterface
    public interface OnLoginFailure {
        void execute(Object state);
    }
}
