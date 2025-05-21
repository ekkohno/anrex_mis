package com.ekkohno.anrex_back.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class ProductDto {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private String collection;
    private String material;
    private String size;
    private Float rating;
    private UUID categoryId;
    private String thumbnail;
    private String slug;
    private String categoryName;
    private UUID categoryTypeId;
    private String categoryTypeName;
    private List<ProductResourceDto> productResources;
    private boolean isNewArrival;
}
