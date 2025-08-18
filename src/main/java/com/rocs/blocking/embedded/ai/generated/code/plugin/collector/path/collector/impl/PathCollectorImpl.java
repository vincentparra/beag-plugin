package com.rocs.blocking.embedded.ai.generated.code.plugin.collector.path.collector.impl;

import com.rocs.blocking.embedded.ai.generated.code.plugin.collector.path.collector.PathCollector;
import org.apache.maven.api.di.Named;
import org.apache.maven.api.di.Singleton;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Singleton
public class PathCollectorImpl implements PathCollector {

    private String sourceRootPath;
    private List<String> changedFiles;

    public void setSourceRootPath(String sourceRootPath) {
        this.sourceRootPath = sourceRootPath;
    }

    public void setChangedFiles(List<String> changedFiles) {
        this.changedFiles = changedFiles;
    }

    @Override
    public List<Path> findPath() {
        try {
            if (changedFiles != null && !changedFiles.isEmpty()) {
                return changedFiles.stream()
                        .map(Paths::get)
                        .filter(path -> path.toString().endsWith(".java"))
                        .collect(Collectors.toList());
            }

            if (sourceRootPath == null || sourceRootPath.trim().isEmpty()) {
                throw new IllegalStateException("source root path is not set and no changed files were provided.");
            }
            Path sourceRoot = Paths.get(this.sourceRootPath);
            return Files.walk(sourceRoot)
                    .filter(path -> path.toString().endsWith(".java"))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException("Error while scanning files", e);
        }
    }
}
