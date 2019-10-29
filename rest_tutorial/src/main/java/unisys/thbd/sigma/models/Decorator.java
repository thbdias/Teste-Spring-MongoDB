package unisys.thbd.sigma.models;

public interface Decorator<T> {

    String toString(T field) throws DecoratorException;


    T fromString(String field) throws DecoratorException;

}
