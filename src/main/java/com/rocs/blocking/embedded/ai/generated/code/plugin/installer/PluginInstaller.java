package com.rocs.blocking.embedded.ai.generated.code.plugin.installer;

import org.apache.maven.plugin.MojoExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PluginInstaller {
    private static final Logger LOGGER = LoggerFactory.getLogger(PluginInstaller.class);
    private static final String WORKFLOW_CONTENT = "name: BEAG\n" +
            "\n" +
            "on:\n" +
            "  pull_request:\n" +
            "    branches: [ \"master\" ]\n" +
            "\n" +
            "jobs:\n" +
            "  scan:\n" +
            "    runs-on: ubuntu-latest\n" +
            "\n" +
            "    steps:\n" +
            "      - name: Checkout code\n" +
            "        uses: actions/checkout@v4\n" +
            "        with:\n" +
            "          fetch-depth: 0\n" +
            "\n" +
            "      - name: Set up JDK 21\n" +
            "        uses: actions/setup-java@v4\n" +
            "        with:\n" +
            "          java-version: '21'\n" +
            "          distribution: 'temurin'\n" +
            "          cache: maven\n" +
            "\n" +
            "      - name: Get changed files\n" +
            "        id: changed-files\n" +
            "        run: |\n" +
            "          BASE_SHA=\"${{ github.event.pull_request.base.sha }}\"\n" +
            "          HEAD_SHA=\"${{ github.event.pull_request.head.sha }}\"\n" +
            "          echo \"Base SHA: $BASE_SHA\"\n" +
            "          echo \"Head SHA: $HEAD_SHA\"\n" +
            "\n" +
            "          CHANGED_FILES=$(git diff --name-only --diff-filter=ACMR \"$BASE_SHA\" \"$HEAD_SHA\" | paste -sd \",\" -)\n" +
            "          echo \"Changed files (excluding deleted): $CHANGED_FILES\"\n" +
            "\n" +
            "          echo \"CHANGED_FILES=$CHANGED_FILES\" >> $GITHUB_ENV\n" +
            "\n" +
            "      - name: Run BEAG on changed files only\n" +
            "        run: |\n" +
            "          echo \"Running BEAG with changed files: $CHANGED_FILES\"\n" +
            "          mvn beag:detect -DchangedFiles=\"$CHANGED_FILES\"\n";

    public void createYMLFile() throws MojoExecutionException {
        File workflowFile = new File(".github/workflows/beag-plugin.yml");

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
