package com.oneune.grapher.service.mapper;

import com.oneune.grapher.service.parser.BlueFeatureCollectionParsingService;
import com.oneune.grapher.service.parser.RedFeatureCollectionParsingService;
import com.oneune.grapher.store.dto.blue.BlueFeatureCollectionDto;
import com.oneune.grapher.store.dto.blue.BlueGeometryDto;
import com.oneune.grapher.store.dto.green.GreenFeaturePropertiesDto;
import com.oneune.grapher.store.dto.green.GreenGeometryDto;
import com.oneune.grapher.store.dto.red.RedFeatureCollectionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GreenMapperService {
    private final BlueFeatureCollectionParsingService blueFeatureCollectionParsingService;

    private final RedFeatureCollectionParsingService redFeatureCollectionParsingService;

    public void generateGreenDto(String blueFilename, String redFilename) {
        BlueFeatureCollectionDto blueFeatureCollectionDto =
                this.blueFeatureCollectionParsingService.parseGeoJsonFile(blueFilename);

        RedFeatureCollectionDto redFeatureCollectionDto =
                this.redFeatureCollectionParsingService.parseRedFeatureCollectionFile(redFilename);

        List<BlueGeometryDto> greenGeometryDtoList = new ArrayList<>();
        GreenFeaturePropertiesDto greenFeaturePropertiesDto = new GreenFeaturePropertiesDto();
        blueFeatureCollectionDto.getFeatures().stream().forEach(feat -> greenGeometryDtoList.add(feat.getGeometry()));
        redFeatureCollectionDto.getFeatures().stream().forEach(feat -> greenFeaturePropertiesDto.setGeomLengthM(feat.getProperties().getGeomLengthM()));
    }
}
