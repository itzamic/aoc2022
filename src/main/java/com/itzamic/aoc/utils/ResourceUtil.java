package com.itzamic.aoc.utils;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ResourceUtil {
    private ResourceUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Path getPath(String path) throws URISyntaxException {
        return Paths.get(Objects.requireNonNull(getResource(path)).toURI());
    }

    private static URL getResource(String path) {
        return Thread.currentThread().getContextClassLoader().getResource(path);
    }
}
