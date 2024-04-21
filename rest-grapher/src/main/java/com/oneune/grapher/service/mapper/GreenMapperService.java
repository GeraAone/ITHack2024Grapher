package com.oneune.grapher.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneune.grapher.service.parser.BlueFeatureCollectionParsingService;
import com.oneune.grapher.service.parser.RedFeatureCollectionParsingService;
import com.oneune.grapher.store.dto.base.CrsDto;
import com.oneune.grapher.store.dto.base.CrsPropertiesDto;
import com.oneune.grapher.store.dto.blue.BlueFeatureCollectionDto;
import com.oneune.grapher.store.dto.green.GreenFeatureCollectionDto;
import com.oneune.grapher.store.dto.green.GreenFeatureDto;
import com.oneune.grapher.store.dto.red.RedFeatureCollectionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class GreenMapperService {
    private final BlueFeatureCollectionParsingService blueFeatureCollectionParsingService;

    private final ObjectMapper objectMapper;
    private final RedFeatureCollectionParsingService redFeatureCollectionParsingService;
    @Value("${resource.geo.dataset.path:#{null}}")
    private String datasetsPrefixPath;

    @Cacheable(value = "greenDataset", key = "#blueFilename + '_' + redFilename")
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
        fillGreenFeatureCollectionByRed(greenFeatureCollectionDto, redFeatureCollectionDto);
        greenGeoJsonFileWrap(greenFeatureCollectionDto);
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
        return greenFeatureCollectionDto;
    }

    public void greenGeoJsonFileWrap(GreenFeatureCollectionDto greenFeatureCollectionDto) {
        try{
            String json = this.objectMapper.writeValueAsString(greenFeatureCollectionDto);
            Path datasetFile = Path.of(datasetsPrefixPath, "kaliningrad_green_WGS84.geojson");
            File file = new File(datasetFile.toString());
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(json);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
