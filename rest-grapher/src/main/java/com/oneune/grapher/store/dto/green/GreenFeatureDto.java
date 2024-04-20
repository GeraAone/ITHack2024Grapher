package com.oneune.grapher.store.dto.green;

import com.oneune.grapher.store.dto.base.supeclass.AbstractFeatureDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class GreenFeatureDto extends AbstractFeatureDto {
    private GreenFeaturePropertiesDto properties;
}
