package com.oneune.grapher.store.dto.green;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GreenCrsDto {
    private String type;
    private GreenCrsPropertiesDto properties;
}
