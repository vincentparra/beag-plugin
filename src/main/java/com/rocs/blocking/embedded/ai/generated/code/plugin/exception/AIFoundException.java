package com.rocs.blocking.embedded.ai.generated.code.plugin.exception;

import org.apache.maven.plugin.MojoFailureException;
/**
 * Exception thrown when AI-generated code is detected during analysis.
 */
public class AIFoundException extends MojoFailureException {
    /**
     * Creates a new AIFoundException with the specified message.
     *
     * @param message a message explaining the cause of the exception
     */
    public AIFoundException(String message) {
        super(message);
    }
}
