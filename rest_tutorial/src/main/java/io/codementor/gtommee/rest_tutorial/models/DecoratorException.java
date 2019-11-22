package io.codementor.gtommee.rest_tutorial.models;

public class DecoratorException extends Exception {

//    private static final long serialVersionUID = 1L;

    public DecoratorException(String message) {
        super(message);
    }


    public DecoratorException(Throwable cause) {
        super(cause);
    }


    public DecoratorException(String message, Throwable cause) {
        super(message, cause);
    }

}
