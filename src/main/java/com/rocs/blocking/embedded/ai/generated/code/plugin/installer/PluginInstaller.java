package com.rocs.blocking.embedded.ai.generated.code.plugin.installer;

import com.rocs.blocking.embedded.ai.generated.code.plugin.exception.FileExistException;
import org.apache.maven.plugin.MojoExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.rocs.blocking.embedded.ai.generated.code.plugin.util.constant.Constant.WORKFLOW_CONTENT;

/**
 * The {@code PluginInstaller} is responsible for generating
 * the default GitHub Actions workflow file used by the BEAG Maven plugin.
 * */
public class PluginInstaller {
    private static final Logger LOGGER = LoggerFactory.getLogger(PluginInstaller.class);

    /**
     * Creates the default BEAG GitHub Actions workflow YAML file.
     *
     * @throws MojoExecutionException if an I/O or file creation error occurs
     * @throws FileExistException if the workflow file already exists in the project directory
     */
    public void createYMLFile() throws MojoExecutionException {
        File workflowFile = new File(".github/workflows/beag-plugin.yml");

        if(workflowFile.exists()){
            throw new FileExistException("file is already exist");
        }
        try {
            try(FileWriter writer = new FileWriter(workflowFile)){
                writer.write(WORKFLOW_CONTENT);
                LOGGER.info("plugin successfully installed");
            }
        } catch (IOException e) {
            LOGGER.error("Error while creating workflow file: " + e.getMessage());
            throw new MojoExecutionException("Error while creating workflow file: " + e.getMessage());
        }
    }
}
