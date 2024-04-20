package com.oneune.grapher.store.dto.blue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlueFeatureCollectionDto {
    private String type;
    private String name;
    private BlueCrsDto crs;
    private List<BlueFeatureDto> features;
}
