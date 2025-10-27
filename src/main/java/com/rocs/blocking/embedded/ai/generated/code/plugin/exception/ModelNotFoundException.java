package com.rocs.blocking.embedded.ai.generated.code.plugin.exception;

import org.apache.maven.plugin.MojoExecutionException;
/**
 * Exception thrown when the machine learning model required for
 * AI detection cannot be found or loaded by the plugin.
 */
public class ModelNotFoundException extends MojoExecutionException {
    /**
     * Constructs a new {@code ModelNotFoundException} with the specified detail message.
     *
     * @param message a message indicating the missing model on its location
     */
    public ModelNotFoundException(String message) {
        super(message);
    }
}
