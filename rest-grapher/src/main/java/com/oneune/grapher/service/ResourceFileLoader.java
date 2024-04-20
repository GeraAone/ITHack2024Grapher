package com.oneune.grapher.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Service
public class ResourceFileLoader {

    @Value("${resource.geo.dataset.path:#{null}}")
    private String datasetsPrefixPath;

    public File getDatasetFileFromResources(String fileName) {
        String datasetFile = Path.of(datasetsPrefixPath, fileName).toString();
        Resource resource = new ClassPathResource(datasetFile);
        try {
            return resource.getFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
