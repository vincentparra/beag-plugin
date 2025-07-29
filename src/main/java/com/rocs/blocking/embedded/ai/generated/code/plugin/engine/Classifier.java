package com.rocs.blocking.ai.generated.code.plugin.engine;

import org.datavec.api.transform.TransformProcess;
import org.datavec.api.transform.analysis.DataAnalysis;
import org.datavec.api.transform.schema.Schema;
import org.datavec.api.transform.transform.normalize.Normalize;
import org.datavec.api.util.ndarray.RecordConverter;
import org.datavec.api.writable.Writable;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.shade.protobuf.ByteString;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class Classifier {
    private final InputStream fileModelSave = getClass().getClassLoader().getResourceAsStream("modelv3.bin");
    private final MultiLayerNetwork model;
    private final DataAnalysis analysis;
    private final Schema targetSchema;

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
   public void inputClassifier(int lines,int chars, int token, int ifStmt, double tokenLength,int method,int methodLength,int switchStmt, int loop){
       List arrayList = Arrays.asList(lines,chars,token,ifStmt,tokenLength,method,methodLength,switchStmt,loop);
       List<Writable> record = RecordConverter.toRecord(schema(),arrayList);
       List<Writable> transformed = transformProcess(analysis).execute(record);
       INDArray data = RecordConverter.toArray(transformed);
       INDArray output = model.output(data, false);
       getPrediction(output);
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
   private void getPrediction(INDArray output){
       double probClass1 = output.getDouble(0, 1);
       double probClass0 = output.getDouble(0,0);
       double threshold = 0.3;
       int predictedClass = probClass1 >= threshold ? 1 : 0;
       System.out.println("\n--- Results ---");
       System.out.println("Threshold: "+threshold*100 +"%");
       System.out.printf("Average Confidence for AI: %.2f%%\n", probClass1 * 100);
       System.out.printf("Average Confidence for Human: %.2f%%\n", probClass0 * 100);
       System.out.println("Classified as: "+(predictedClass == 1? "Contains AI Generated Code":"Human Written Code"));
   }
}
