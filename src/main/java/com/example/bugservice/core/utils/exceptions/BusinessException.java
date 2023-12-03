package com.example.bugservice.core.utils.exceptions;


/**
 * The exception thrown when a Business error happens.
 *
 * @author msg systems AG;
 * @since 19.1.2
 */
public class BusinessException extends RuntimeException {

    /**
     * The message inside the exception.
     */
    private ExceptionMessage exceptionMessage;

    public BusinessException(final ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    /**
     * Getter.
     *
     * @return exceptionMessage
     */
    public ExceptionMessage getExceptionMessage() {
        return this.exceptionMessage;
    }
}
