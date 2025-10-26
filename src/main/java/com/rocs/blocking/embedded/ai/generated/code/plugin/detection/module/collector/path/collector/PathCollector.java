package com.rocs.blocking.embedded.ai.generated.code.plugin.detection.module.collector.path.collector;

import com.rocs.blocking.embedded.ai.generated.code.plugin.exception.SourceRootNotFoundException;

import java.nio.file.Path;
import java.util.List;
/**
 * this class handles the path finding
 */
public interface PathCollector {
    /**
     * {@code findpath} handles the path finding
     * @return a list of Path
     */
    List<Path> findPath() throws SourceRootNotFoundException;
    /**
     * this set the source root path of the project
     *
     * @param sourceRootPath is the path set by the user
     * */
    void setSourceRootPath(String sourceRootPath);
    /**
     * this set the change file path of the project
     *
     * @param changedFiles is the change file path set by the user
     * */
    void setChangedFiles(List<String> changedFiles);
    void setExcludedFiles(List<String> excludedFiles);
}