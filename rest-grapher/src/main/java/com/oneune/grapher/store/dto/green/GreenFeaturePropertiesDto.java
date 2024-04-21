package com.oneune.grapher.store.dto.green;

import com.oneune.grapher.store.dto.red.RedFeaturePropertiesDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class GreenFeaturePropertiesDto extends RedFeaturePropertiesDto {
    private int roadLengthM;
}
