package com.ekkohno.anrex_back.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class ProductResourceDto {

    private UUID id;
    private String name;
    private String url;
    private String type;
    private  Boolean isPrimary;
}
