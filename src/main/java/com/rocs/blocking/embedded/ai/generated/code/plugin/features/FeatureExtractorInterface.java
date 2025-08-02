package com.rocs.blocking.embedded.ai.generated.code.plugin.features;

import org.apache.maven.plugin.MojoFailureException;

import java.nio.file.Path;

/**
 * this handles the method used for extracting features
 */
public interface FeatureExtractorInterface {
    /**
     * this method used to count the number of line for each java class
     * @param path is the source path of the java class
     * @return the total number of lines of the class
     */
    int countNumberOfLines(Path path);

    /**
     * this method used to count the number of characters inside the java class
     * @param path is the source path of the java class
     * @return the total number of character for each class
     */
    int countNumberOfChar(Path path);
    /**
     * this method used to count the number of token for each java class
     * @param path is the source path of the java class
     * @return the total number of token for each class
     */
    int countNumberOfToken(Path path);

    /**
     * this method used to count the average token length for each class
     * @param numChar is the total number of characters
     * @param numToken is the total number of tokens
     * @return a value when @code numChar is divided with @code numToken
     */
    double countAverageTokenLength(int numChar, int numToken);
    /**
     * this method used to count the number of if statement for each java class
     * @param path is the source path of the java class
     * @return the total number of if statement for each class
     */
    int countIfStatement(Path path);
    /**
     * this method used to count the number of methods for each java class
     * @param path is the source path of the java class
     * @return the total number of method for each class
     */
    int countMethods(Path path);
    /**
     * this method used to count the total length of methods for each java class
     * @param path is the source path of the java class
     * @return the total length of the method for each class
     */
    int averageMethodLength(Path path);
    /**
     * this method used to count the number of switch statement for each java class
     * @param path is the source path of the java class
     * @return the total number of switch statement for each class
     */
    int countSwitchStmt(Path path);
    /**
     * this method used to count the number of loop statement for each java class
     * @param path is the source path of the java class
     * @return the total number of loop statement for each class
     */
    int countLoops(Path path);
    /**
     * this method used to determine if the java class is Interface
     * @param path is the source path of the java class
     * @return true if the java class is Interface and false if a not an Interface
     */
    boolean isInterface(Path path);
}
