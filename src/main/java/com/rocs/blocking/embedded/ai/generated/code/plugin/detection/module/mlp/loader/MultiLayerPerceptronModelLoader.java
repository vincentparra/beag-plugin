package com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module.mlp.loader;

import com.rocs.blocking.embedded.ai.generated.code.plugin.exception.ModelNotFoundException;

import java.io.File;
import java.io.InputStream;
/**
 * This class is responsible for loading a pre-trained models {@code MultiLayerPerceptron}
 * a model from an input stream and saving it to a temporary file.
 */
public class MultiLayerPerceptronModelLoader {
    /**
     * Loads the saved model from the provided input stream and writes it to
     * a temporary file for later use.
     *
     * If the input stream is {@code null}, a {@code  ModelNotFoundException} is thrown.
     *
     * @param fileModelSave the input stream containing the serialized model data
     * @return a temporary {@code File} reference pointing to the saved model
     * @throws ModelNotFoundException if the model file cannot be found in the classpath
     * @throws RuntimeException if an I/O error occurs during model loading
     */
    public File loadSaveModel(InputStream fileModelSave){
        File tempModelFile;
        try {
            InputStream modelStream = fileModelSave;
            if (modelStream == null) {
                throw new ModelNotFoundException("model.bin not found in classpath");
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
