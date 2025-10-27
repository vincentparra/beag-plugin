package com.rocs.blocking.embedded.ai.generated.code.plugin.mojo;

import com.rocs.blocking.embedded.ai.generated.code.plugin.installer.PluginInstaller;
import org.apache.maven.api.di.Inject;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
/**
 *  The {@code PluginInstallerMojo} class is a Maven Mojo (plugin goal) responsible for
 *  initializing the GitHub action file required for the BEAG plugin.
 * */
@Mojo(name = "install")
public class PluginInstallerMojo extends AbstractMojo {
    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;
    @Inject
    private PluginInstaller pluginInstaller;
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        pluginInstaller = new PluginInstaller();
        pluginInstaller.createYMLFile();
    }
}
