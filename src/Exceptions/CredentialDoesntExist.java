package Exceptions;

public class CredentialDoesntExist extends RuntimeException {
    public CredentialDoesntExist(String message) {
        super(message);
    }
}
