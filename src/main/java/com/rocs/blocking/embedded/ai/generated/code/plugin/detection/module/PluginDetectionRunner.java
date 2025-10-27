package com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module;

import com.rocs.blocking.embedded.ai.generated.code.plugin.blocking.module.reports.feature.impl.ReportFeatureImpl;
import com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module.collector.path.collector.PathCollector;
import com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module.collector.path.collector.impl.PathCollectorImpl;
import com.rocs.blocking.embedded.ai.generated.code.plugin.mojo.AIDetectorMojo;
import org.apache.maven.api.di.Inject;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 * The {@code PluginDetectionRunner} class serves as the core detection engine for the
 *  BEAG Maven plugin, responsible for handling file collection, filtering,
 *  and report generation of potential AI-generated code within a Java-based project.
 * */
public class PluginDetectionRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(PluginDetectionRunner.class);
    private String changedFiles;
    private String sourceRootPath;
    private boolean isFailable;
    private double threshold;
    private String excludedFiles;
    @Inject
    private ReportFeatureImpl report;
    @Inject
    private PathCollector pathFinder;
    /**
     * Constructs a new {@code PluginDetectionRunner} instance with the provided configuration parameters.
     *
     * @param changedFiles   comma-separated list of modified files to analyze (nullable)
     * @param sourceRootPath the root path of the source directory
     * @param isFailable     whether to fail the build if AI-generated code is detected
     * @param threshold      minimum threshold (0–1.0) for AI detection confidence
     * @param excludedFiles  comma-separated list of files to exclude from detection
     */
    public PluginDetectionRunner(String changedFiles, String sourceRootPath, boolean isFailable, double threshold, String excludedFiles) {
        this.changedFiles = changedFiles;
        this.sourceRootPath = sourceRootPath;
        this.isFailable = isFailable;
        this.threshold = threshold;
        this.excludedFiles = excludedFiles;
    }
    /**
     * Public wrapper method for initiating the plugin's detection routine.
     *
     * This method delegates execution to {@code pluginRunner()}, providing an entry point for external callers.
     *
     * @throws MojoExecutionException if a runtime or configuration error occurs
     * @throws MojoFailureException   if detection results trigger a build failure
     */
    public void getPluginRunner() throws MojoExecutionException, MojoFailureException {
        pluginRunner();
    }

    private void pluginRunner() throws MojoExecutionException, MojoFailureException {
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
                LOGGER.warn("No Java files to scan, skipping detection");
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
