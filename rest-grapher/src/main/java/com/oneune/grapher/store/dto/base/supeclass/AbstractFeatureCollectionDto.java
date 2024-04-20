package com.oneune.grapher.store.dto.base.supeclass;

import com.oneune.grapher.store.dto.base.CrsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
public abstract class AbstractFeatureCollectionDto {
    private String type;
    private String name;
    private CrsDto crs;
}
