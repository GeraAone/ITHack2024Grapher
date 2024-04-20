package com.oneune.grapher.store.dto.blue;

import com.oneune.grapher.store.dto.base.GeometryDto;
import com.oneune.grapher.store.dto.base.supeclass.AbstractFeatureDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlueFeatureDto extends AbstractFeatureDto {
    private BlueFeaturePropertiesDto properties;
}
