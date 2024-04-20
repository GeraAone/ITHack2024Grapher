package com.oneune.grapher.service.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneune.grapher.service.ResourceFileLoader;
import com.oneune.grapher.store.dto.red.RedFeatureCollectionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;


@Service
@RequiredArgsConstructor
@Log4j2
public class RedFeatureCollectionParsingService {

    private final ObjectMapper objectMapper;
    private final ResourceFileLoader resourceFileLoader;


    public RedFeatureCollectionDto parseRedFeatureCollectionFile(String geoJsonFilename) {

        File datasetFile = resourceFileLoader.getDatasetFileFromResources(geoJsonFilename);

        try {
            return objectMapper.readValue(datasetFile, RedFeatureCollectionDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
