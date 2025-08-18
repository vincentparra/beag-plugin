package com.rocs.blocking.embedded.ai.generated.code.plugin.mlp.utils.helper;

import com.rocs.blocking.embedded.ai.generated.code.plugin.mlp.loader.MultiLayerPerceptronModelLoader;
import org.datavec.api.transform.analysis.DataAnalysis;
import org.datavec.api.transform.schema.Schema;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class LoadEngineHelper {
    private File tempModelFile;
    private final InputStream fileModelSave = getClass().getClassLoader().getResourceAsStream("BeagModel3.1.bin");
    public LoadEngineHelper() {
        MultiLayerPerceptronModelLoader loader = new MultiLayerPerceptronModelLoader();
        this.tempModelFile = loader.loadSaveModel(fileModelSave);
    }

    public MultiLayerNetwork getModel() throws IOException {
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
