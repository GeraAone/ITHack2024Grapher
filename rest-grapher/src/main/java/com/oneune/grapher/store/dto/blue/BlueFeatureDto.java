package com.oneune.grapher.store.dto.blue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlueFeatureDto {
    private String type;
    private BlueFeaturePropertiesDto properties;
    private BlueGeometryDto geometry;
}
