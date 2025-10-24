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
}