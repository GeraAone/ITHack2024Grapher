package com.oneune.grapher.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneune.grapher.service.ResourceFileService;
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
import com.oneune.grapher.store.dto.red.RedFeatureDto;
import com.oneune.grapher.store.dto.red.RedFeaturePropertiesDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
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
@Log4j2
public class GreenMapperService {

    private final BlueFeatureCollectionParsingService blueFeatureCollectionParsingService;
    private final RedFeatureCollectionParsingService redFeatureCollectionParsingService;
    private final ResourceFileService resourceFileService;
    private final ModelMapper modelMapper;
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

        log.info("Начало алгоритма");
        fillGreenFeatureCollectionByBlue(greenFeatureCollectionDto, blueFeatureCollectionDto, redFeatureCollectionDto);
        log.info("Конец алгоритма");
        fillGreenFeatureCollectionByRed(greenFeatureCollectionDto, redFeatureCollectionDto);
        this.resourceFileService.writeDatasetToResources("kaliningrad_green_WGS84.geojson", greenFeatureCollectionDto);

        return greenFeatureCollectionDto;
    }


    public void fillGreenFeatureCollectionByBlue(GreenFeatureCollectionDto greenFeatureCollectionDto,
                                                 BlueFeatureCollectionDto blueFeatureCollectionDto, RedFeatureCollectionDto redFeatureCollectionDto) {
        blueFeatureCollectionDto.getFeatures().forEach(feat -> {
            GreenFeatureDto greenFeatureDto = new GreenFeatureDto();
            greenFeatureDto.setType("Feature");
            greenFeatureDto.setGeometry(feat.getGeometry());
            greenFeatureDto.setProperties(checkRedBlueCoordinatesAndSetProperties(error, greenFeatureDto.getGeometry(), redFeatureCollectionDto));
            //проверка на то, имеет ли данная дорога атрибуты по выбранной ошибке
            if (greenFeatureDto.getProperties() == null) {
                greenFeatureDto.setHasProperties(false);
            } else greenFeatureDto.setHasProperties(true);
            greenFeatureCollectionDto.getFeatures().add(greenFeatureDto);
        });
    }

    public void fillGreenFeatureCollectionByRed(GreenFeatureCollectionDto greenFeatureCollectionDto,
                                                RedFeatureCollectionDto redFeatureCollectionDto) {
    }

    /**
     *
     * @param error
     * @param blueGeometry
     * @param redFeatureCollectionDto
     * @return greenFeatureCollectionDto
     * Comments: method returns required DTO to process in future
     */

    public GreenFeaturePropertiesDto checkRedBlueCoordinatesAndSetProperties(Double error, GeometryDto blueGeometry,
                                                                             RedFeatureCollectionDto redFeatureCollectionDto) {

        boolean flag = true;
        //создаем лист координат синей дороги, переданной
        List<List<Double>> blueList = blueGeometry.getCoordinates().get(0);
        //cписок всех найденных дорог, подходящие под ошибку
        List<RedFeatureDto> redFeatureDtoList = new ArrayList<>();
        //первый цикл для прохода по всем дорогам "Red" графа,второй сравнения в каждом с "Blue" графом
        for (int i = 0; i < redFeatureCollectionDto.getFeatures().size(); i++) {
            List<List<Double>> redList = redFeatureCollectionDto.getFeatures().get(i).getGeometry().getCoordinates().get(0);
            if(Math.abs(redList.size() - blueList.size()) <=2) {
                for (int j = 0; j < Math.min(redList.size(), blueList.size()); j++) {
                    if (!(Math.abs(redList.get(j).get(0) - blueList.get(j).get(0)) <= error && Math.abs(redList.get(j).get(1) - blueList.get(j).get(1)) <= error)) {
                        flag = false;
                        break;
                    }
                }
            }
            else flag = false;
            if (flag != false) {
                redFeatureDtoList.add(redFeatureCollectionDto.getFeatures().get(i));
            }
        }
        //проверка количества найденных дорог
        if (redFeatureDtoList.size() == 1) {
//            return modelMapper.map(redFeatureDtoList.get(0), GreenFeaturePropertiesDto.class);
            GreenFeaturePropertiesDto greenFeaturePropertiesDto = new GreenFeaturePropertiesDto();
            greenFeaturePropertiesDto.setRoadName(redFeatureDtoList.get(0).getProperties().getRoadName());
            greenFeaturePropertiesDto.setId(redFeatureDtoList.get(0).getProperties().getId());
            greenFeaturePropertiesDto.setFinishM(redFeatureDtoList.get(0).getProperties().getFinishM());
            greenFeaturePropertiesDto.setStartM(redFeatureDtoList.get(0).getProperties().getStartM());
            greenFeaturePropertiesDto.setRoadPartId(redFeatureDtoList.get(0).getProperties().getRoadPartId());
            return greenFeaturePropertiesDto;
        } else {
            return null;
        }
    }
}
