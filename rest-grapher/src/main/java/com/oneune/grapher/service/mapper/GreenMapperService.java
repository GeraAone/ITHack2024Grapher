package com.oneune.grapher.service.mapper;

import com.oneune.grapher.service.parser.BlueFeatureCollectionParsingService;
import com.oneune.grapher.service.parser.RedFeatureCollectionParsingService;
import com.oneune.grapher.store.dto.base.GeometryDto;
import com.oneune.grapher.store.dto.blue.BlueFeatureCollectionDto;
import com.oneune.grapher.store.dto.green.GreenFeatureCollectionDto;
import com.oneune.grapher.store.dto.green.GreenFeaturePropertiesDto;
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

    public GreenFeatureCollectionDto generateGreenDto(String blueFilename, String redFilename) {
        BlueFeatureCollectionDto blueFeatureCollectionDto =
                this.blueFeatureCollectionParsingService.parseGeoJsonFile(blueFilename);

        RedFeatureCollectionDto redFeatureCollectionDto =
                this.redFeatureCollectionParsingService.parseRedFeatureCollectionFile(redFilename);

        List<GeometryDto> greenGeometryDtoList = new ArrayList<>();
        GreenFeatureCollectionDto greenFeatureCollectionDto = new GreenFeatureCollectionDto();
        blueFeatureCollectionDto.getFeatures().forEach(feat -> greenGeometryDtoList.add(feat.getGeometry()));
        redFeatureCollectionDto.getFeatures().forEach(feat ->
                greenFeatureCollectionDto.getFeatures().forEach(green ->
                        green.getProperties().setGeomLengthM(feat.getProperties().getGeomLengthM())));
        return greenFeatureCollectionDto;
    }
}
