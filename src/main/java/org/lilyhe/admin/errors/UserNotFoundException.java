package org.lilyhe.admin.errors;

// write custom exception classes to handle specific exceptions to display user-friendly messages
// good to separate it out
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
