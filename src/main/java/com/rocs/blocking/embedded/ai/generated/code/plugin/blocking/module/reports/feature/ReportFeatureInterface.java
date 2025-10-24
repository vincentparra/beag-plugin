package com.rocs.blocking.embedded.ai.generated.code.plugin.blocking.module.reports.feature;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * this handles the reporting of the plugin
 */
public interface ReportFeatureInterface {
    /**
     * Executes classification on a list of Java source files retrieved from a GitHub repository.
     * <p>
     * This method is responsible for processing each file, extracting feature, and running the
     * trained classifier to determine if the code may contain AI-generated patterns or not.
     * </p>
     *
     * @param javaFiles the list of Java source files retrieved from the GitHub repository
     * @param isFailable is a boolean that specifies if the plugin will fail the build process when detected an AI generated Code
     * @throws MojoExecutionException if an error occurs during plugin execution
     * @throws MojoFailureException if the plugin fails to complete the classification task
     */
    void getReports(List<Path> javaFiles,boolean isFailable,double threshold) throws MojoExecutionException, MojoFailureException, IOException;
}
