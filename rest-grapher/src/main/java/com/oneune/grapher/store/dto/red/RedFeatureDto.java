package com.oneune.grapher.store.dto.red;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RedFeatureDto {
    private String type;
    private RedFeaturePropertiesDto properties;
    private RedGeometryDto geometry;
}
