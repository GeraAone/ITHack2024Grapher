package com.oneune.grapher.store.dto.base.supeclass;


import com.oneune.grapher.store.dto.base.GeometryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class AbstractFeatureDto {
    private String type;
    private GeometryDto geometry;
}
