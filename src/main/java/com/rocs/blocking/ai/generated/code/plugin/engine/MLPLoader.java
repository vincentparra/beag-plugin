package com.rocs.blocking.ai.generated.code.plugin.engine;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MLPLoader {
    public MultiLayerNetwork machineLoader(){
        MultiLayerNetwork multiLayerNetwork;
        try (InputStream fileModelSave = getClass().getClassLoader().getResourceAsStream("modelv2.bin")){
//            File fileModelSave = new File("src/main/resources/modelv2.bin");
            multiLayerNetwork  = ModelSerializer.restoreMultiLayerNetwork(fileModelSave);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return multiLayerNetwork;
    }
}
