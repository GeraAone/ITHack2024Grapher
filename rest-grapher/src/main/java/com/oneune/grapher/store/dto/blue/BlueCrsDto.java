package com.oneune.grapher.store.dto.blue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlueCrsDto {
    private String type;
    private BlueCrsPropertiesDto properties;
}
