package com.rocs.blocking.embedded.ai.generated.code.plugin.mojo;

import com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module.PluginDetectionRunner;
import com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module.collector.path.collector.PathCollector;
import com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module.collector.path.collector.impl.PathCollectorImpl;
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

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        PluginDetectionRunner pluginDetectionRunner = new PluginDetectionRunner(changedFiles,sourceRootPath,isFailable,threshold,excludedFiles);
        pluginDetectionRunner.getPluginRunner();
    }
}
