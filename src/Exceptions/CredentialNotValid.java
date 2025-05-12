package Exceptions;

public class CredentialNotValid extends RuntimeException {
    public CredentialNotValid(String message) {
        super(message);
    }
}
