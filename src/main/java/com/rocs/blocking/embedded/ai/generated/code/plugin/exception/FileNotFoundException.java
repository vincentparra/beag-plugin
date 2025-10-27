package com.rocs.blocking.embedded.ai.generated.code.plugin.exception;

import org.apache.maven.plugin.MojoExecutionException;
/**
 * Exception thrown when a required source file cannot be found
 * during the plugin execution process.
 */
public class FileNotFoundException extends MojoExecutionException {
    /**
     * Constructs a new {@code FileNotFoundException} with the specified detail message.
     *
     * @param message a message explaining which file was not found
     */
    public FileNotFoundException(String message) {
        super(message);
    }
}
