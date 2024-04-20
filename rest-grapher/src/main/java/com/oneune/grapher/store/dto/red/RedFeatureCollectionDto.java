package com.oneune.grapher.store.dto.red;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class RedFeatureCollectionDto {
    private String type;
    private String name;
    private RedCrsDto crs;
    private List<RedFeatureDto> features;
}
