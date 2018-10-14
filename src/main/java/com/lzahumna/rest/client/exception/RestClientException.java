package com.lzahumna.rest.client.exception;

/**
 * Throw if request to Gists API fails
 *
 * @author Liudmyla Zahumna
 * since 12/10/2018.
 */
public class RestClientException extends RuntimeException {

    public RestClientException(String message) {
        super(message);
    }

    public RestClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestClientException(Throwable cause) {
        super(cause);
    }
}
