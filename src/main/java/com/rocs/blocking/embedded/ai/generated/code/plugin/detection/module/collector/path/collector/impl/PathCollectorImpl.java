package com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module.collector.path.collector.impl;

import com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module.collector.path.collector.PathCollector;
import com.rocs.blocking.embedded.ai.generated.code.plugin.exception.SourceRootNotFoundException;
import org.apache.maven.api.di.Named;
import org.apache.maven.api.di.Singleton;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Singleton
public class PathCollectorImpl implements PathCollector {

    private String sourceRootPath;
    private List<String> changedFiles;
    private List<String> excludedFiles;
   @Override
    public void setSourceRootPath(String sourceRootPath) {
        this.sourceRootPath = sourceRootPath;
    }
    @Override
    public void setChangedFiles(List<String> changedFiles) {
        this.changedFiles = changedFiles;
    }

    @Override
    public void setExcludedFiles(List<String> excludedFiles) {
        this.excludedFiles = excludedFiles;
    }

    @Override
    public List<Path> findPath() throws SourceRootNotFoundException {
        List<Path> files;
        try {
            if (changedFiles != null && !changedFiles.isEmpty()) {
                files = changedFiles.stream()
                        .map(Paths::get)
                        .filter(path -> path.toString().endsWith(".java"))
                        .collect(Collectors.toList());
            }else{
                if (sourceRootPath == null || sourceRootPath.trim().isEmpty()) {
                    throw new SourceRootNotFoundException("source root path is not set and no changed files were provided.");
                }
                Path sourceRoot = Paths.get(this.sourceRootPath);
                files = Files.walk(sourceRoot)
                        .filter(path -> path.toString().endsWith(".java"))
                        .collect(Collectors.toList());
            }
            if (excludedFiles != null && !excludedFiles.isEmpty()) {
                files = files.stream()
                        .filter(path -> excludedFiles.stream()
                                .noneMatch(excluded -> {
                                    Path excludedPath = Paths.get(excluded).normalize().toAbsolutePath();
                                    Path currentPath = path.normalize().toAbsolutePath();
                                    return currentPath.equals(excludedPath);
                                }))
                        .collect(Collectors.toList());
            }
            return files;
        } catch (IOException e) {
            throw new RuntimeException("Error while scanning files", e);
        }

    }

}
