package com.rocs.blocking.embedded.ai.generated.code.plugin.blocking.module.reports.feature.impl;

import com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module.domain.input.Input;
import com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module.mlp.classifier.Classifier;
import com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module.collector.feature.collector.FeatureExtractorInterface;
import com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module.collector.feature.collector.impl.FeatureExtractorInterfaceImpl;
import com.rocs.blocking.embedded.ai.generated.code.plugin.blocking.module.reports.feature.ReportFeatureInterface;
import com.rocs.blocking.embedded.ai.generated.code.plugin.blocking.module.reports.prediction.Prediction;
import org.apache.maven.api.di.Named;
import org.apache.maven.api.di.Singleton;
import org.apache.maven.plugin.MojoFailureException;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * This class is the implementation of {@code ReportFeatureInterface}
 * these handles the reporting of the plugin and serve as the text-based UI that prints out the
 */
@Named
@Singleton
public class ReportFeatureImpl implements ReportFeatureInterface {
    @Override
    public void getReports(List<Path> javaFiles,boolean isFailable, double threshold) throws MojoFailureException, IOException{
        int numChar,numToken;
        boolean isInterface;

        for(Path path: javaFiles){
            FeatureExtractorInterface featureExtractor = new FeatureExtractorInterfaceImpl();
            Classifier classifier = new Classifier();
            Prediction prediction = new Prediction();
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
            isInterface = featureExtractor.isInterface(path);
            System.out.println("<--------------------------------------------------------------------------------->");

            if(!isInterface){
                Input inputs = new Input();
                inputs.setLines(featureExtractor.countNumberOfLines(path));
                inputs.setChars(featureExtractor.countNumberOfChar(path));
                inputs.setToken(featureExtractor.countNumberOfToken(path));
                inputs.setIfStmt(featureExtractor.countIfStatement(path));
                inputs.setTokenLength(featureExtractor.countAverageTokenLength(numChar,numToken));
                inputs.setMethod(featureExtractor.countMethods(path));
                inputs.setMethodLength(featureExtractor.averageMethodLength(path));
                inputs.setSwitchStmt( featureExtractor.countSwitchStmt(path));
                inputs.setLoop(featureExtractor.countLoops(path));
                INDArray output = classifier.outputClassifier(inputs);
                System.out.println(prediction.getPrediction(output,isFailable,threshold));
            }else{
                System.out.println("Unable to measure interface: no logic inside");
            }
            System.out.println("<--------------------------------------------------------------------------------->");
        }
    }

}
