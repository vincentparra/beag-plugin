package com.rocs.blocking.embedded.ai.generated.code.plugin.engine;

import org.apache.maven.plugin.MojoFailureException;
import org.datavec.api.transform.TransformProcess;
import org.datavec.api.transform.analysis.DataAnalysis;
import org.datavec.api.transform.schema.Schema;
import org.datavec.api.transform.transform.normalize.Normalize;
import org.datavec.api.util.ndarray.RecordConverter;
import org.datavec.api.writable.Writable;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * This class handles the model used for classification
 */
public class Classifier {
    private final Logger LOGGER = LoggerFactory.getLogger(Classifier.class);
    private final InputStream fileModelSave = getClass().getClassLoader().getResourceAsStream("modelv3.bin");
    private final MultiLayerNetwork model;
    private final DataAnalysis analysis;
    private final Schema targetSchema;
    /**
     * Constructs a {@code Classifier} by loading a pre-trained DL4J model from a binary file.
     * This constructor attempts to retrieve a model binary file (expected to be assigned to {@code fileModelSave}),
     * creates a temporary file to store its contents, and then restores a {@link org.deeplearning4j.nn.multilayer.MultiLayerNetwork}
     * instance from it. Additionally, it extracts associated analysis and schema metadata embedded in the model file.
     *
     *
     * Expected resources inside the model file:
     *
     *  <li>{@code model.bin} — the serialized DL4J model</li>
     *  <li>{@code "analysis"} — a JSON string representing {@link org.datavec.api.transform.analysis.DataAnalysis}</li>
     *  <li>{@code "schema"} — a JSON string representing {@link org.datavec.api.transform.schema.Schema}</li>
     *
     *
     *
     * @throws IllegalStateException if the {@code fileModelSave} input stream is {@code null}
     * @throws RuntimeException if any exception occurs during model restoration or file handling
     */
    public Classifier() {
        try {
            InputStream modelStream = fileModelSave;
            if (modelStream == null) {
                throw new IllegalStateException("model.bin not found in classpath");
            }

            File tempModelFile = File.createTempFile("model", ".bin");
            tempModelFile.deleteOnExit();

            java.nio.file.Files.copy(
                    modelStream,
                    tempModelFile.toPath(),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING
            );

            this.model = ModelSerializer.restoreMultiLayerNetwork(tempModelFile);
            String analysisJson = (String) ModelSerializer.getObjectFromFile(tempModelFile, "analysis");
            this.analysis = DataAnalysis.fromJson(analysisJson);
            String schemaJson = (String) ModelSerializer.getObjectFromFile(tempModelFile, "schema");
            this.targetSchema = Schema.fromJson(schemaJson);

        } catch (Exception e) {
            throw new RuntimeException("Error initializing Classifier", e);
        }
    }
    /**
     * Processes and classifies a set of extracted code metrics using the pre-trained model.
     *
     * This method prepares a feature vector from the provided metrics, transforms the input
     * according to the original data schema and analysis used during training, and passes
     * the processed data to the neural network for prediction.
     *
     * @param lines        the number of lines in the source code file
     * @param chars        the number of characters in the source code file
     * @param token        the total number of tokens in the file
     * @param ifStmt       the number of 'if' statements
     * @param tokenLength  the average length of tokens
     * @param method       the number of methods in the file
     * @param methodLength the average length of method bodies
     * @param switchStmt   the number of 'switch' statements
     * @param loop         the number of loop constructs (e.g., for, while)
     * @param isFailable   a boolean value that specifies if the detector will fail on build when there is an AI generated code
     * @throws RuntimeException if transformation or prediction fails
     */
   public void inputClassifier(int lines,int chars, int token, int ifStmt, double tokenLength,int method,int methodLength,int switchStmt, int loop,boolean isFailable) throws MojoFailureException {
       List arrayList = Arrays.asList(lines,chars,token,ifStmt,tokenLength,method,methodLength,switchStmt,loop);
       List<Writable> record = RecordConverter.toRecord(schema(),arrayList);
       List<Writable> transformed = transformProcess(analysis).execute(record);
       INDArray data = RecordConverter.toArray(transformed);
       INDArray output = model.output(data, false);
       getPrediction(output,isFailable);
   }

   private Schema schema(){
       Schema schema = new Schema.Builder()
               .addColumnInteger("num_lines")
               .addColumnInteger("num_char")
               .addColumnInteger("num_tokens")
               .addColumnInteger("num_IfStmt")
               .addColumnDouble("ave_tokens_length")
               .addColumnInteger("numMethods")
               .addColumnInteger("avgMethodLength")
               .addColumnInteger("numSwitchStmt")
               .addColumnInteger("numOfLoops")
               .build();
       return schema;
   }

   private TransformProcess transformProcess(DataAnalysis analysis){
       String[] newOrder = targetSchema.getColumnNames().stream().filter(it -> !it.equals("is_ai_generated")).toArray(String[]::new);
       return new TransformProcess.Builder(schema())
               .removeColumns("numSwitchStmt")
               .normalize("num_lines", Normalize.Standardize, analysis)
               .normalize("num_char", Normalize.Standardize, analysis)
               .normalize("num_tokens", Normalize.Standardize, analysis)
               .normalize("num_IfStmt", Normalize.Standardize, analysis)
               .normalize("ave_tokens_length", Normalize.Standardize, analysis)
               .normalize("numMethods", Normalize.Standardize, analysis)
               .normalize("numOfLoops",Normalize.Standardize,analysis)
               .normalize("avgMethodLength",Normalize.Standardize,analysis)
               .reorderColumns(newOrder)
               .build();
   }
   private void getPrediction(INDArray output, boolean isFailable) throws MojoFailureException {
       double probClass1 = output.getDouble(0, 1);
       double probClass0 = output.getDouble(0,0);
       double threshold = 0.3;
       int predictedClass = probClass1 >= threshold ? 1 : 0;
       System.out.println("\n--- Results ---");
       System.out.println("Threshold: "+threshold*100 +"%");
       System.out.printf("Average Confidence for AI: %.2f%%\n", probClass1 * 100);
       System.out.printf("Average Confidence for Human: %.2f%%\n", probClass0 * 100);
       System.out.println("Classification: "+(predictedClass == 1 ? "Contains AI generated Code":"Human Written Code"));

       if(predictedClass == 1 && isFailable){
            throw new MojoFailureException("Contains AI generated Code");
       }else{
           System.out.println("Human Written Code");
       }
   }
}
