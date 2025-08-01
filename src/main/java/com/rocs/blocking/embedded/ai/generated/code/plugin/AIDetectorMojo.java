package com.rocs.blocking.embedded.ai.generated.code.plugin;

import com.rocs.blocking.embedded.ai.generated.code.plugin.collector.impl.PathFinderImpl;
import com.rocs.blocking.embedded.ai.generated.code.plugin.reports.FeatureReportInterface;
import com.rocs.blocking.embedded.ai.generated.code.plugin.reports.impl.FeatureReportImpl;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mojo(name = "detect", defaultPhase = LifecyclePhase.COMPILE)
public class AIDetectorMojo extends AbstractMojo {

    @Parameter(property = "changedFiles")
    private String changedFiles;

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    private static Logger LOGGER = LoggerFactory.getLogger(AIDetectorMojo.class);

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            PathFinderImpl pathFinder = new PathFinderImpl();
            if (changedFiles != null && !changedFiles.trim().isEmpty()) {
                List<String> fileList = Arrays.stream(changedFiles.split(","))
                        .map(String::trim)
                        .collect(Collectors.toList());
                LOGGER.info("Changed files detected: " + fileList);
                pathFinder.setChangedFiles(fileList);
            } else {
                LOGGER.info("No changed files passed. Scanning entire project.");
                pathFinder.setPath("src/main");
            }

            List<Path> javaFiles = pathFinder.findPath();

            if (javaFiles.isEmpty()) {
                LOGGER.warn("No Java files found to scan.");
                return;
            }

            LOGGER.info("Found " + javaFiles.size() + " Java file(s) to analyze.");

            FeatureReportInterface report = new FeatureReportImpl();
            report.getReports(javaFiles);

        } catch (Exception e) {
            throw new MojoExecutionException("Error while running AI Detector plugin", e);
        }
    }
}
