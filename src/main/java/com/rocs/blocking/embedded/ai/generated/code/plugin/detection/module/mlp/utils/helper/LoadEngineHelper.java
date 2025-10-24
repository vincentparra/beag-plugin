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

import static com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module.mlp.utils.Constant.MODEL_SAVE;

public class LoadEngineHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadEngineHelper.class);
    private File tempModelFile;
    private final InputStream fileModelSave = getClass().getClassLoader().getResourceAsStream(MODEL_SAVE);
    public LoadEngineHelper() {
        MultiLayerPerceptronModelLoader loader = new MultiLayerPerceptronModelLoader();
        this.tempModelFile = loader.loadSaveModel(fileModelSave);
    }

    public MultiLayerNetwork getModel() throws IOException {
        LOGGER.info("Using model: "+MODEL_SAVE);
        return ModelSerializer.restoreMultiLayerNetwork(tempModelFile);
    }
    public DataAnalysis getAnalysis(){
        String analysisJson = (String) ModelSerializer.getObjectFromFile(tempModelFile, "analysis");
        return DataAnalysis.fromJson(analysisJson);
    }

    public Schema getTargetSchema() {
        String schemaJson = (String) ModelSerializer.getObjectFromFile(tempModelFile, "schema");
        return Schema.fromJson(schemaJson);
    }
}
