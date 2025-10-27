package com.rocs.blocking.embedded.ai.generated.code.plugin.exception;

import org.apache.maven.plugin.MojoExecutionException;
/**
 * Exception thrown when the plugin cannot locate the source root directory
 * of the target project during the scanning or analysis process.
 */
public class SourceRootNotFoundException extends MojoExecutionException {
    /**
     * Constructs a new {@code SourceRootNotFoundException} with the specified detail message.
     *
     * @param message a message indicating the missing source root or its expected path
     */
    public SourceRootNotFoundException(String message) {
        super(message);
    }
}
