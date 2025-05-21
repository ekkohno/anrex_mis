package com.ekkohno.anrex_back.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class CategoryTypeDto {
    private UUID id;
    private String name;
    private String code;
    private String description;
}
