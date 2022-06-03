package com.verylinkedin.core.auth.exception;

/**
 * OtpCache Exception
 */
public class DiabServiceException extends RuntimeException {

    /**
     * OtpCache Exception with error message
     *
     * @param errorMessage error message
     */
    public DiabServiceException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * OtpCache Exception with error message and throwable
     *
     * @param errorMessage error message
     * @param throwable    error
     */
    public DiabServiceException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

}
