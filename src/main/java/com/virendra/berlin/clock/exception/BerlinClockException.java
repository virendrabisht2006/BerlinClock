package com.virendra.berlin.clock.exception;

/**
 * Created by Virendra Singh on 06/05/18.
 */
public class BerlinClockException extends RuntimeException {
    public BerlinClockException(String errorMessage) {
        super(errorMessage);
    }

    public BerlinClockException(String errorMessage, Exception exception) {
        super(errorMessage, exception);
    }

    public BerlinClockException(Exception exception) {
        super(exception);
    }
}
