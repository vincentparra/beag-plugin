package com.rocs.blocking.ai.generated.code.plugin.collector.impl;

import com.rocs.blocking.ai.generated.code.plugin.collector.PathFinderInterface;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PathFinderImpl implements PathFinderInterface {
    private Path root = Paths.get("src/main");
    @Override
    public List<Path> findPath() {
        try{
            List<Path> pathStream = Files.walk(root).collect(Collectors.toList());
            return pathStream.stream().filter(path -> path.toString().endsWith(".java")).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
