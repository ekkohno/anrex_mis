package com.ekkohno.anrex_back.controllers;

import com.ekkohno.anrex_back.dto.ProductBatchDto;
import com.ekkohno.anrex_back.dto.ProductDto;
import com.ekkohno.anrex_back.entities.Product;
import com.ekkohno.anrex_back.services.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(required = false,name = "categoryId",value = "categoryId") UUID categoryId,@RequestParam(required = false,name = "typeId",value = "typeId") UUID typeId, @RequestParam(required = false) String slug){
        List<ProductDto> productList = new ArrayList<>();
        if(StringUtils.isNotBlank(slug)){
            ProductDto productDto = productService.getProductBySlug(slug);
            productList.add(productDto);
        }
        else {
            productList = productService.getAllProducts(categoryId, typeId);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID id){
        ProductDto productDto = productService.getProductById(id);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    //   create Product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto){
        Product product = productService.addProduct(productDto);
        return new ResponseEntity<>(product,HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDto productDto){
        Product product = productService.updateProduct(productDto);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    /*@PostMapping("/batch")
    public ResponseEntity<List<Product>> createProducts(@RequestBody ProductBatchDto productBatchDto) {
        List<Product> products = productService.addProducts(productBatchDto.getProducts());
        return new ResponseEntity<>(products, HttpStatus.CREATED);
    }*/

}