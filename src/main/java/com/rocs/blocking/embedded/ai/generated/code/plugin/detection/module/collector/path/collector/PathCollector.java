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
     * this set the list of change file path of the project
     *
     * @param changedFiles is the list of file change file path set by the user
     * */
    void setChangedFiles(List<String> changedFiles);
    /**
     * Sets the list of file names or paths that should be excluded
     * from the path collection process.
     *
     * @param excludedFiles a list of file names or patterns to ignore during path collection
     */
    void setExcludedFiles(List<String> excludedFiles);
}