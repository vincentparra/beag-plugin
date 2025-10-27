package com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module.mlp.utils.helper;

import com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module.mlp.loader.MultiLayerPerceptronModelLoader;
import org.datavec.api.transform.analysis.DataAnalysis;
import org.datavec.api.transform.schema.Schema;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static com.rocs.blocking.embedded.ai.generated.code.plugin.util.constant.Constant.MODEL_SAVE;
/**
 * Helper class responsible for loading the serialized {@code MultiLayerNetwork}
 * model and its associated metadata (analysis and schema) from the classpath.
 *
 * This class initializes the model upon construction and provides access to
 * the trained network, data analysis, and schema
 */
public class LoadEngineHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadEngineHelper.class);
    private final File tempModelFile;
    private final InputStream fileModelSave = getClass().getClassLoader().getResourceAsStream(MODEL_SAVE);
    /**
     * Constructs a new {@code LoadEngineHelper} and initializes the
     * {@code MultiLayerPerceptronModelLoader} to load the saved model.
     */
    public LoadEngineHelper() {
        MultiLayerPerceptronModelLoader loader = new MultiLayerPerceptronModelLoader();
        this.tempModelFile = loader.loadSaveModel(fileModelSave);
    }
    /**
     * Loads and returns the trained {@code MultiLayerNetwork} model.
     *
     * @return the restored {@code MultiLayerNetwork} instance
     * @throws IOException if an error occurs while reading the model file
     */
    public MultiLayerNetwork getModel() throws IOException {
        LOGGER.info("Using model: "+MODEL_SAVE);
        return ModelSerializer.restoreMultiLayerNetwork(tempModelFile);
    }
    /**
     * Retrieves the {@code DataAnalysis} object embedded within the model file.
     *
     * @return the {@code DataAnalysis} instance associated with the model
     */
    public DataAnalysis getAnalysis(){
        String analysisJson = (String) ModelSerializer.getObjectFromFile(tempModelFile, "analysis");
        return DataAnalysis.fromJson(analysisJson);
    }
    /**
     * Retrieves the {@code Schema} object embedded within the model file.
     *
     * @return the {@code Schema} representing the target data structure
     */
    public Schema getTargetSchema() {
        String schemaJson = (String) ModelSerializer.getObjectFromFile(tempModelFile, "schema");
        return Schema.fromJson(schemaJson);
    }
}
