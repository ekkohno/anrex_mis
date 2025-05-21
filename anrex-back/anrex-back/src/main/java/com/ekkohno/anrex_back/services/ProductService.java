package com.ekkohno.anrex_back.services;

import com.ekkohno.anrex_back.dto.ProductDto;
import com.ekkohno.anrex_back.entities.Product;

import java.util.List;
import java.util.UUID;


public interface ProductService {

    public Product addProduct(ProductDto product);
    public List<ProductDto> getAllProducts(UUID categoryId, UUID typeId);
    ProductDto getProductBySlug(String slug);
    ProductDto getProductById(UUID id);
    Product updateProduct(ProductDto productDto);
    //List<Product> addProducts(List<ProductDto> products);
    Product fetchProductById(UUID uuid) throws Exception;

}