package com.oneune.grapher.store.dto.red;

import com.oneune.grapher.store.dto.base.GeometryDto;
import com.oneune.grapher.store.dto.base.supeclass.AbstractFeatureDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class RedFeatureDto extends AbstractFeatureDto {
    private RedFeaturePropertiesDto properties;
}
