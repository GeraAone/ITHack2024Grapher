package com.oneune.grapher.store.dto.base;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CrsDto {
    private String type;
    private CrsPropertiesDto properties;
}
