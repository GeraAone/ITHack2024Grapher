package com.oneune.grapher.service.mapper;

import com.oneune.grapher.service.parser.BlueFeatureCollectionParsingService;
import com.oneune.grapher.service.parser.RedFeatureCollectionParsingService;
import com.oneune.grapher.store.dto.base.CrsDto;
import com.oneune.grapher.store.dto.base.CrsPropertiesDto;
import com.oneune.grapher.store.dto.base.GeometryDto;
import com.oneune.grapher.store.dto.blue.BlueFeatureCollectionDto;
import com.oneune.grapher.store.dto.green.GreenFeatureCollectionDto;
import com.oneune.grapher.store.dto.green.GreenFeatureDto;
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

        CrsDto crsDto = CrsDto.builder().type("name").properties(new CrsPropertiesDto("urn:ogc:def:crs:OGC:1.3:CRS84")).build();
        GreenFeatureCollectionDto greenFeatureCollectionDto = GreenFeatureCollectionDto
                .builder().type("FeatureCollection").name("kaliningrad_region_4326").crs(crsDto).build();
        greenFeatureCollectionDto.setFeatures(new ArrayList<>());
        fillGreenFeatureCollectionByBlue(greenFeatureCollectionDto, blueFeatureCollectionDto);
        return greenFeatureCollectionDto;
    }

    public GreenFeatureCollectionDto fillGreenFeatureCollectionByBlue(GreenFeatureCollectionDto greenFeatureCollectionDto,
                                                                      BlueFeatureCollectionDto blueFeatureCollectionDto) {
        blueFeatureCollectionDto.getFeatures().forEach(feat -> {
            GreenFeatureDto greenFeatureDto = new GreenFeatureDto();
            greenFeatureDto.setType("MultiLineString");
            greenFeatureDto.setGeometry(feat.getGeometry());
            greenFeatureCollectionDto.getFeatures().add(greenFeatureDto);
        });
        return greenFeatureCollectionDto;
    }

    public GreenFeatureCollectionDto fillGreenFeatureCollectionByRed(GreenFeatureCollectionDto greenFeatureCollectionDto,
                                                                      RedFeatureCollectionDto redFeatureCollectionDto) {
            GreenFeatureDto greenFeatureDto = new GreenFeatureDto();
            greenFeatureDto.setType("MultiLineString");
        return greenFeatureCollectionDto;
    }

}
