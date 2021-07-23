package intospring.demo.exception;

public class InvalidAccountOperation extends RuntimeException {
    public InvalidAccountOperation() {
    }

    public InvalidAccountOperation(String message) {
        super(message);
    }

    public InvalidAccountOperation(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAccountOperation(Throwable cause) {
        super(cause);
    }
}
