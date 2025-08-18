package com.rocs.blocking.embedded.ai.generated.code.plugin.mlp.loader;

import java.io.File;
import java.io.InputStream;

public class MultiLayerPerceptronModelLoader {

    public File loadSaveModel(InputStream fileModelSave){
        File tempModelFile;
        try {
            InputStream modelStream = fileModelSave;
            if (modelStream == null) {
                throw new IllegalStateException("model.bin not found in classpath");
            }

            tempModelFile = File.createTempFile("model", ".bin");
            tempModelFile.deleteOnExit();

            java.nio.file.Files.copy(
                    modelStream,
                    tempModelFile.toPath(),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING
            );
        } catch (Exception e) {
            throw new RuntimeException("Error initializing Classifier", e);
        }
        return tempModelFile;
    }
}
