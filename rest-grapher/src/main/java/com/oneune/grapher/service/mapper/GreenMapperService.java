package com.oneune.grapher.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class GreenMapperService {
    private final BlueFeatureCollectionParsingService blueFeatureCollectionParsingService;

    private final ObjectMapper objectMapper;
    private final RedFeatureCollectionParsingService redFeatureCollectionParsingService;
    @Value("${resource.geo.dataset.path:#{null}}")
    private String datasetsPrefixPath;

    @Value("${error:#{null}}")
    private Double error;

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
        fillGreenFeatureCollection(greenFeatureCollectionDto, blueFeatureCollectionDto, redFeatureCollectionDto);
        fillGreenFeatureCollectionByRed(greenFeatureCollectionDto, redFeatureCollectionDto);
        wrapGreenGeoJsonFile(greenFeatureCollectionDto);
        return greenFeatureCollectionDto;
    }

    public GreenFeatureCollectionDto fillGreenFeatureCollection(GreenFeatureCollectionDto greenFeatureCollectionDto,
                                                                BlueFeatureCollectionDto blueFeatureCollectionDto,
                                                                RedFeatureCollectionDto redFeatureCollectionDto) {
        blueFeatureCollectionDto.getFeatures().forEach(feat -> {
            GreenFeatureDto greenFeatureDto = new GreenFeatureDto();
            greenFeatureDto.setType("MultiLineString");
            greenFeatureDto.setGeometry(feat.getGeometry());
//            greenFeatureDto.setProperties(checkRedBlueCoordinatesAndSetProperties(error ,greenFeatureDto.getGeometry(), redFeatureCollectionDto));
            if(greenFeatureDto.getProperties() == null){
                greenFeatureDto.setHasProperties(false);
            }
            greenFeatureCollectionDto.getFeatures().add(greenFeatureDto);
        });
        return greenFeatureCollectionDto;
    }

    public GreenFeatureCollectionDto fillGreenFeatureCollectionByRed(GreenFeatureCollectionDto greenFeatureCollectionDto,
                                                                      RedFeatureCollectionDto redFeatureCollectionDto) {
        return greenFeatureCollectionDto;
    }

    public void wrapGreenGeoJsonFile(GreenFeatureCollectionDto greenFeatureCollectionDto) {
        try{
            String json = this.objectMapper.writeValueAsString(greenFeatureCollectionDto);
            Path datasetFile = Path.of(datasetsPrefixPath);
            File file = new File(datasetFile.toString(), "kaliningrad_green_WGS84.geojson");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(json);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

//    public GreenFeaturePropertiesDto checkRedBlueCoordinatesAndSetProperties (Double error, GeometryDto blueGeometry,
//                                                             RedFeatureCollectionDto redFeatureCollectionDto) {
//        List<List<Double>> blueList = blueGeometry.getCoordinates().get(0);
//            for(int i = 0; i < redFeatureCollectionDto.getFeatures().size(); i++) {
//                List<List<Double>> redList = redFeatureCollectionDto.getFeatures().get(i).getGeometry().getCoordinates().get(0);
//                IntStream.range(0, Math.min(redList.size(), blueList.size()))
//                        .forEach(j -> {
//                            if( Math.abs(redList.get(j).get(0) - blueList.get(j).get(0)) <= error
//                                    && Math.abs(redList.get(j).get(1) - blueList.get(j).get(1)) <= error) {
//                                return redFeatureCollectionDto.getFeatures().get(i).getProperties();
//                            }
//                        });
//                return null;
//            }
//    }
}
