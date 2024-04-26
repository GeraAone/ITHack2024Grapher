package com.oneune.grapher.service.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneune.grapher.service.ResourceFileService;
import com.oneune.grapher.store.dto.red.RedFeatureCollectionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
@RequiredArgsConstructor
@Log4j2
public class RedFeatureCollectionParsingService {

    private final ObjectMapper objectMapper;
    private final ResourceFileService resourceFileService;


    public RedFeatureCollectionDto parseRedFeatureCollectionFile(String geoJsonFilename) {

        File datasetFile = resourceFileService.getDatasetFileFromResources(geoJsonFilename);

        try {
            return objectMapper.readValue(datasetFile, RedFeatureCollectionDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
