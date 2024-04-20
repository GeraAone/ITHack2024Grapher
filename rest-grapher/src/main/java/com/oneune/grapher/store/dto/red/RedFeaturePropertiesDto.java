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
    private Object start;
    private Object finish;
    private int startM;
    private int finishM;
    private int roadLengthM;
    private String roadName;
    private int valueOfRoadGid;
    private boolean isSkeleton;
    private int zoom;
    private int style;
    private boolean isChecked;
    private double geomLengthM;
    private String box;
    private String geom1000;
    private String geom100;
    private String geom10;
    private String begDate;
    private Object endDate;
    private int flag;
}
