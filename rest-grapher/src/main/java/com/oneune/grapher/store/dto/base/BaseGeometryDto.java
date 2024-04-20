package com.oneune.grapher.store.dto.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class BaseGeometryDto {
    private String type;
    private List<List<List<Double>>> coordinates;
}
