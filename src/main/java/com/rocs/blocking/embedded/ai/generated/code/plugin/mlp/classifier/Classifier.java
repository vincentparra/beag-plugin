package com.rocs.blocking.embedded.ai.generated.code.plugin.mlp.classifier;

import com.rocs.blocking.embedded.ai.generated.code.plugin.input.Input;
import com.rocs.blocking.embedded.ai.generated.code.plugin.mlp.utils.helper.LoadEngineHelper;
import org.apache.maven.plugin.MojoFailureException;
import org.datavec.api.transform.TransformProcess;
import org.datavec.api.transform.analysis.DataAnalysis;
import org.datavec.api.transform.schema.Schema;
import org.datavec.api.transform.transform.normalize.Normalize;
import org.datavec.api.util.ndarray.RecordConverter;
import org.datavec.api.writable.Writable;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * This class handles the model used for classification
 */
public class Classifier {
    private final Logger LOGGER = LoggerFactory.getLogger(Classifier.class);
    private final LoadEngineHelper loadEngine;
    private final MultiLayerNetwork model;
    /**
     * Constructs a {@code Classifier} by loading a pre-trained DL4J model from a binary file.
     * This constructor attempts to retrieve a model binary file,
     * creates a temporary file to store its contents, and then restores a {@link org.deeplearning4j.nn.multilayer.MultiLayerNetwork}
     * instance from it. Additionally, it extracts associated analysis and schema metadata embedded in the model file.

     */
    public Classifier() throws IOException {
        this.loadEngine = new LoadEngineHelper();
        this.model = loadEngine.getModel();
    }

    public INDArray outputClassifier(Input inputs){
        INDArray data = RecordConverter.toArray(transformed(inputs));
        return model.output(data, false);
    }

    private List<Writable> transformed(Input inputs){
        List arrayList = Arrays.asList(inputs.getLines(),inputs.getChars(),inputs.getToken(),inputs.getIfStmt(),inputs.getTokenLength(),inputs.getMethod(),inputs.getMethodLength(),inputs.getMethodLength(),inputs.getLoop());
        DataAnalysis analysis = loadEngine.getAnalysis();
        List<Writable> record = RecordConverter.toRecord(schema(), arrayList);
        return transformProcess(analysis).execute(record);
   }

   private Schema schema(){
       return new Schema.Builder()
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
   }

   private TransformProcess transformProcess(DataAnalysis analysis){
       Schema targetSchema = loadEngine.getTargetSchema();
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



}
