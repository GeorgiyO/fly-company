package org.example.session;

public class NoAccessException extends Exception {
    public NoAccessException(String message) {
        super("Need '" + message + "' access-level");
    }
}
