package com.oneune.grapher.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
@RequiredArgsConstructor
public class ResourceFileService {

    private final Path fullResourcesDirectoryPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources");

    @Value("${resource.geo.dataset.path:#{null}}")
    private String datasetsPrefixPath;

    private final ObjectMapper objectMapper;

    public File getDatasetFileFromResources(String fileName) {
        String datasetFile = Path.of(datasetsPrefixPath, fileName).toString();
        Resource resource = new ClassPathResource(datasetFile);
        try {
            return resource.getFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> void writeDatasetToResources(String filename, T writableObject) {
        try {
            Path path = Path.of(this.fullResourcesDirectoryPath.toString(), this.datasetsPrefixPath, filename);
            String stringObject = this.objectMapper.writeValueAsString(writableObject);
            Files.writeString(path, stringObject);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
