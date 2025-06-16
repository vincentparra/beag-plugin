package com.rocs.blocking.ai.generated.code.plugin.reports.impl;

import com.rocs.blocking.ai.generated.code.plugin.collector.impl.PathFinderImpl;
import com.rocs.blocking.ai.generated.code.plugin.engine.Classifier;
import com.rocs.blocking.ai.generated.code.plugin.features.FeatureExtractorInterface;
import com.rocs.blocking.ai.generated.code.plugin.features.impl.FeatureExtractorInterfaceImpl;
import com.rocs.blocking.ai.generated.code.plugin.reports.FeatureReportInterface;

import java.nio.file.Path;
import java.util.List;

public class FeatureReportImpl implements FeatureReportInterface {
    @Override
    public void getFeatures() {
        PathFinderImpl pathFinder = new PathFinderImpl();
        List<Path> paths = pathFinder.findPath();
        int numChar,numToken;
        boolean isInterface;
        for(Path path: paths){
            FeatureExtractorInterface featureExtractor = new FeatureExtractorInterfaceImpl();
            Classifier classifier = new Classifier();
            System.out.println(path);
            System.out.println("Number of lines        : "+featureExtractor.countNumberOfLines(path));
            System.out.println("Number of Character    : "+featureExtractor.countNumberOfChar(path));
            numChar = featureExtractor.countNumberOfChar(path);
            System.out.println("Number of Tokens       : "+featureExtractor.countNumberOfToken(path));
            numToken = featureExtractor.countNumberOfToken(path);
            System.out.println("Number of if statement : "+featureExtractor.countIfStatement(path));
            System.out.println("Token length           : "+featureExtractor.countAverageTokenLength(numChar,numToken));
            System.out.println("Number of Methods      : "+featureExtractor.countMethods(path));
            System.out.println("Average method length  : "+featureExtractor.averageMethodLength(path));
            System.out.println("Number of Switch       : "+featureExtractor.countSwitchStmt(path));
            System.out.println("Number of Loops        : "+featureExtractor.countLoops(path));
            System.out.println("<--------------------------------------------------------------------------------->");
            isInterface = featureExtractor.isInterface(path);
            if(!isInterface){
                classifier.inputClassifier(featureExtractor.countNumberOfLines(path),
                        featureExtractor.countNumberOfChar(path),
                        featureExtractor.countNumberOfToken(path),
                        featureExtractor.countIfStatement(path),
                        featureExtractor.countAverageTokenLength(numChar,numToken),
                        featureExtractor.countMethods(path),
                        featureExtractor.averageMethodLength(path),
                        featureExtractor.countSwitchStmt(path),
                        featureExtractor.countLoops(path));
            }else{
                System.out.println("Unable to measure interface: no logic inside");
            }
            System.out.println("<--------------------------------------------------------------------------------->");
        }
    }
}
