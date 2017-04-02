package de.livingfire.kanboard.exception;

public class KanboardException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public KanboardException(String message,
                             Throwable cause) {
        super(message, cause);
    }

    public KanboardException(String message) {
        super(message);
    }

    public KanboardException(Throwable cause) {
        super(cause);
    }
}
