package club.wljyes.service;

public class UserException extends Exception {
    public UserException() {
        super();
    }

    UserException(String message) {
        super(message);
    }
}
