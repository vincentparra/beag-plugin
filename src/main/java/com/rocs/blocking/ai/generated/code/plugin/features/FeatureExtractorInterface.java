package com.rocs.blocking.ai.generated.code.plugin.features;

import java.nio.file.Path;

public interface FeatureExtractorInterface {
    int countNumberOfLines(Path path);
    int countNumberOfChar(Path path);
    int countNumberOfToken(Path path);
    double countAverageTokenLength(int numChar, int numToken);
    int countIfStatement(Path path);
    int countMethods(Path path);
    int averageMethodLength(Path path);
    int countSwitchStmt(Path path);
    int countLoops(Path path);
    boolean isInterface(Path path);
}
