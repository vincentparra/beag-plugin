package com.rocs.blocking.embedded.ai.generated.code.plugin;

import com.rocs.blocking.embedded.ai.generated.code.plugin.reports.FeatureReportInterface;
import com.rocs.blocking.embedded.ai.generated.code.plugin.reports.impl.FeatureReportImpl;
import org.apache.maven.api.di.Inject;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;


@Mojo(name = "detect",defaultPhase = LifecyclePhase.COMPILE)
public class AIDetectorMojo extends AbstractMojo {
    @Inject
    FeatureReportInterface featureReport = new FeatureReportImpl();
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        featureReport.getReports();
    }
}