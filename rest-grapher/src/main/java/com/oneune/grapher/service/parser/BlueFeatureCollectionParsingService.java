package com.oneune.grapher.service.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneune.grapher.store.dto.blue.BlueFeatureCollectionDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
@Log4j2
public class BlueFeatureCollectionParsingService {
    private final ObjectMapper objectMapper;

    public BlueFeatureCollectionDto parseGeoJsonFile(@NonNull String filePath) {
        try {
            File file = new File(filePath);
            return objectMapper.readValue(file, BlueFeatureCollectionDto.class);
        } catch (Exception e) {
            log.error("Ошибка парсинга файла: " + e.getMessage());
            throw new RuntimeException("Invalid path name!");
        }
    }
}
