package com.oneune.grapher.store.dto.red;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class RedFeaturePropertiesDto {
    private int id;
    private int roadId;
    private int roadPartId;
    private int startM;
    private int finishM;
    private String roadName;
}
