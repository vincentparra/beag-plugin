package com.rocs.blocking.embedded.ai.generated.code.plugin.collector.impl;

import com.rocs.blocking.embedded.ai.generated.code.plugin.collector.PathFinderInterface;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PathFinderImpl implements PathFinderInterface {

    private String path;
    private List<String> changedFiles;

    public void setPath(String path) {
        this.path = path;
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
                        .filter(p -> p.toString().endsWith(".java"))
                        .collect(Collectors.toList());
            }

            if (path == null || path.trim().isEmpty()) {
                throw new IllegalStateException("Path is not set and no changed files were provided.");
            }
            Path root = Paths.get(path);
            return Files.walk(root)
                    .filter(p -> p.toString().endsWith(".java"))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException("Error while scanning files", e);
        }
    }
}
