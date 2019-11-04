package io.codementor.gtommee.rest_tutorial.models;

public interface Decorator<T> {

    String toString(T field) throws DecoratorException;


    T fromString(String field) throws DecoratorException;

}
