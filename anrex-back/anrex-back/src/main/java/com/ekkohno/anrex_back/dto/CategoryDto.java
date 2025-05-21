package com.ekkohno.anrex_back.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CategoryDto {

    private UUID id;
    private String name;
    private String code;
    private String description;
    private List<CategoryTypeDto> categoryTypeList;

}
