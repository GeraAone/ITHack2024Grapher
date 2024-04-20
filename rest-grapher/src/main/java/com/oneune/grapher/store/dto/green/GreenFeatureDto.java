package com.oneune.grapher.store.dto.green;

import com.oneune.grapher.store.dto.blue.BlueGeometryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GreenFeatureDto {
    private String type;
    private GreenFeaturePropertiesDto properties;
    private BlueGeometryDto geometry;
}
