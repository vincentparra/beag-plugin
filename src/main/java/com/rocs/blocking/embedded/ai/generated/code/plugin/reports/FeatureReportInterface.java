package com.rocs.blocking.embedded.ai.generated.code.plugin.reports;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.nio.file.Path;
import java.util.List;

public interface FeatureReportInterface {
    void getReports(List<Path> javaFiles) throws MojoExecutionException, MojoFailureException;
}
