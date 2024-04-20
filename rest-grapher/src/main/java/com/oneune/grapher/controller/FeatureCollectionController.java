package com.oneune.grapher.controller;


import com.oneune.grapher.service.parser.BlueFeatureCollectionParsingService;
import com.oneune.grapher.service.parser.RedFeatureCollectionParsingService;
import com.oneune.grapher.store.dto.blue.BlueFeatureCollectionDto;
import com.oneune.grapher.store.dto.red.RedFeatureCollectionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("feature-collection")
@RequiredArgsConstructor
public class FeatureCollectionController {

    private final RedFeatureCollectionParsingService redFeatureCollectionParsingService;
    private final BlueFeatureCollectionParsingService blueFeatureCollectionParsingService;

    @PostMapping("red/{filename}")
    public void parseRedFeaturesCollection(@PathVariable String filename) {
        RedFeatureCollectionDto redFeatureCollection =
                this.redFeatureCollectionParsingService.parseRedFeatureCollectionFile(filename);
        String testBreakPoint = "";
    }

    @PostMapping("blue/{filename}")
    public BlueFeatureCollectionDto parseBlueFeaturesCollection(@PathVariable String filename) {
        return this.blueFeatureCollectionParsingService.parseGeoJsonFile(filename);
    }

}
