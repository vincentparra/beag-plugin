package com.rocs.blocking.embedded.ai.generated.code.plugin.exception;
/**
 * Exception thrown when an attempt is made to create or access a file
 * that already exists in the expected location.
 */
public class FileExistException extends RuntimeException {
    /**
     * Constructs a new {@code FileExistException} with the specified detail message.
     *
     * @param message a message explaining the cause of the exception
     */
    public FileExistException(String message) {
        super(message);
    }
}
