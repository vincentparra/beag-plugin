package com.rocs.blocking.embedded.ai.generated.code.plugin.util.constant;
/**
 * The {@code Constant} class serves as a centralized repository for application-wide
 * constant values used across the BEAG Maven Plugin.
 * */
public class Constant {
    /**
     * the file name for beag model
     */
    public static final String MODEL_SAVE = "beag-model-v3.6.bin";
    /**
     * the content of the GitHub Action workflow
     * */
    public static final String WORKFLOW_CONTENT = "name: BEAG\n" +
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
}
