package com.ekkohno.anrex_back.dto;

import com.ekkohno.anrex_back.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.rmi.server.UID;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDetail {

    private UUID id;
    private Product product;
    private Integer quantity;
    private Double itemPrice;
}