package com.oneune.grapher.store.dto.green;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GreenFeatureCollectionDto {
    private String type;
    private String name;
    private GreenCrsDto crs;
    private List<GreenFeatureDto> features;
}
