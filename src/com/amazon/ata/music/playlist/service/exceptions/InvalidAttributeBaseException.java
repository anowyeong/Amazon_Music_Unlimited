package com.amazon.ata.music.playlist.service.exceptions;

public class InvalidAttributeBaseException extends RuntimeException {

    private static final long serialVersionUID = 7485964658205534175L;

    /**
     * Exception with no message or cause.
     */
    public InvalidAttributeBaseException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public InvalidAttributeBaseException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public InvalidAttributeBaseException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public InvalidAttributeBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
