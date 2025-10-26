package com.rocs.blocking.embedded.ai.generated.code.plugin;

import com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module.collector.path.collector.PathCollector;
import com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module.collector.path.collector.impl.PathCollectorImpl;
import com.rocs.blocking.embedded.ai.generated.code.plugin.exception.FileNotFoundException;
import com.rocs.blocking.embedded.ai.generated.code.plugin.blocking.module.reports.feature.impl.ReportFeatureImpl;
import org.apache.maven.api.di.Inject;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * This class serves as the Mojo for the BEAG plugin that detects {@code AI generated code}
 * this class extends with the {@code AbstractMojo} Interface to implement the {@code execute} class
 * making the plugin runnable using this class as an entry point
 * */
@Mojo(name = "detect", defaultPhase = LifecyclePhase.COMPILE)
public class AIDetectorMojo extends AbstractMojo {
    private static final Logger LOGGER = LoggerFactory.getLogger(AIDetectorMojo.class);

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    @Parameter(property = "changedFiles")
    private String changedFiles;

    @Parameter(property = "sourceRoot",defaultValue = "src/main")
    private String sourceRootPath;

    @Parameter(property = "failOnAi",defaultValue = "true")
    private boolean isFailable;

    @Parameter(property = "threshold", defaultValue = "0.30")
    private double threshold;

    @Parameter(property = "excludedFile")
    private String excludedFiles;

    @Inject
    private ReportFeatureImpl report;
    @Inject
    private PathCollector pathFinder;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        pathFinder = new PathCollectorImpl();
        try {
            if(excludedFiles != null && !excludedFiles.trim().isBlank()){
                List<String> fileList = Arrays.stream(excludedFiles.split(","))
                        .map(String::trim)
                        .toList();
                LOGGER.info("Excluded files detected: " + fileList);
                pathFinder.setExcludedFiles(fileList);
            }
            if (changedFiles != null && !changedFiles.trim().isEmpty()) {
                List<String> fileList = Arrays.stream(changedFiles.split(","))
                        .map(String::trim)
                        .collect(Collectors.toList());
                LOGGER.info("Changed files detected: " + fileList);
                pathFinder.setChangedFiles(fileList);
            } else {
                LOGGER.info("No changed files passed. Scanning entire project.");
                pathFinder.setSourceRootPath(sourceRootPath);
            }

            List<Path> javaFiles = pathFinder.findPath();

            if (javaFiles.isEmpty()) {
                LOGGER.warn("No Java files found to scan.");
                throw new FileNotFoundException("No Java files found to scan.");
            }

            LOGGER.info("Found " + javaFiles.size() + " Java file(s) to analyze.");
            report = new ReportFeatureImpl();
            report.getReports(javaFiles,isFailable,threshold);

        } catch (NullPointerException e) {
            throw new MojoExecutionException("Error while running AI Detector plugin", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
