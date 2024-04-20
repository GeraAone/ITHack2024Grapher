package com.oneune.grapher.controller;


import com.oneune.grapher.service.mapper.GreenMapperService;
import com.oneune.grapher.service.parser.BlueFeatureCollectionParsingService;
import com.oneune.grapher.service.parser.RedFeatureCollectionParsingService;
import com.oneune.grapher.store.dto.blue.BlueFeatureCollectionDto;
import com.oneune.grapher.store.dto.green.GreenFeatureCollectionDto;
import com.oneune.grapher.store.dto.red.RedFeatureCollectionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("feature-collection")
@RequiredArgsConstructor
public class FeatureCollectionController {

    private final RedFeatureCollectionParsingService redFeatureCollectionParsingService;
    private final BlueFeatureCollectionParsingService blueFeatureCollectionParsingService;

    private final GreenMapperService greenMapperService;

    @PostMapping("red/{filename}")
    public RedFeatureCollectionDto parseRedFeaturesCollection(@PathVariable String filename) {
        RedFeatureCollectionDto redFeatureCollection =
                this.redFeatureCollectionParsingService.parseRedFeatureCollectionFile(filename);
        return redFeatureCollection;
    }

    @PostMapping("blue/{filename}")
    public BlueFeatureCollectionDto parseBlueFeaturesCollection(@PathVariable String filename) {
        return this.blueFeatureCollectionParsingService.parseGeoJsonFile(filename);
    }

    @PostMapping("green")
    public GreenFeatureCollectionDto getGreenFeaturesCollection(@RequestParam String blueFilename,
                                                                @RequestParam String redFilename) {
        return this.greenMapperService.generateGreenDto(blueFilename, redFilename);
    }
}
