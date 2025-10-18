package com.rocs.blocking.embedded.ai.generated.code.plugin.reports.prediction;

import com.rocs.blocking.embedded.ai.generated.code.plugin.exception.AIFoundException;
import com.rocs.blocking.embedded.ai.generated.code.plugin.input.Input;
import com.rocs.blocking.embedded.ai.generated.code.plugin.mlp.classifier.Classifier;
import org.apache.maven.plugin.MojoFailureException;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.io.IOException;

public class Prediction {
    public String getPrediction(INDArray output, boolean isFailable,double threshold) throws MojoFailureException {
        double probClass1 = output.getDouble(0, 1);
        double probClass0 = output.getDouble(0,0);
        int predictedClass = probClass1 >= threshold ? 1 : 0;

        if(probClass1 == 1){
            probClass0 = 0.0;
        }
        if(probClass0 == 1){
            probClass1 = 0.0;
        }
        if(predictedClass == 1 && isFailable){
            System.out.println("\n--- Results ---\n" +
                    "Threshold: " + threshold * 100 + "%\n" +
                    "Average Confidence for AI: " + probClass1 * 100 + "%\n" +
                    "Average Confidence for Human: " + probClass0 * 100 + "%\n" +
                    "Classification: " + (predictedClass == 1
                    ? "Contains AI generated Code"
                    : "Human Written Code"));
            throw new AIFoundException("Contains AI generated Code");
        }
        output.close();

        return "\n--- Results ---\n" +
                "Threshold: " + threshold * 100 + "%\n" +
                "Average Confidence for AI: " + probClass1 * 100 + "%\n" +
                "Average Confidence for Human: " + probClass0 * 100 + "%\n" +
                "Classification: " + (predictedClass == 1
                ? "Contains AI generated Code"
                : "Human Written Code");
    }
}
