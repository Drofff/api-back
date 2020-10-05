package ua.ozzy.apiback.exception;

public class ApiBackException extends RuntimeException {

    public ApiBackException(String message) {
        super(message);
    }

    public ApiBackException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiBackException(Throwable cause) {
        super(cause);
    }

}
