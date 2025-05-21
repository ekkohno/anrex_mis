package com.ekkohno.anrex_back.dto;

import java.util.List;

public class ProductBatchDto {
    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    private List<ProductDto> products;
}
