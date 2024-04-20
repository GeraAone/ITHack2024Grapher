package com.oneune.grapher.store.dto.green;

import com.oneune.grapher.store.dto.base.supeclass.AbstractFeatureCollectionDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class GreenFeatureCollectionDto extends AbstractFeatureCollectionDto {
    private List<GreenFeatureDto> features;
}
