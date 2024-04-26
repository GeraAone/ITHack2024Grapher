package com.oneune.grapher.store.dto.red;

import com.oneune.grapher.store.dto.base.CrsDto;
import com.oneune.grapher.store.dto.base.supeclass.AbstractFeatureCollectionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class RedFeatureCollectionDto extends AbstractFeatureCollectionDto {
    private List<RedFeatureDto> features;
}
