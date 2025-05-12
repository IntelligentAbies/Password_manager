package Exceptions;

public class DatabaseNotSetupped extends RuntimeException {
    public DatabaseNotSetupped(String message) {
        super(message);
    }
}
