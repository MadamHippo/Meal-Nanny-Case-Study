package org.lilyhe.admin.errors;

// custom exception for product not found
public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
