package com.ekkohno.anrex_back.services;

import com.ekkohno.anrex_back.dto.ProductDto;

import com.ekkohno.anrex_back.dto.ProductResourceDto;
import com.ekkohno.anrex_back.entities.Category;
import com.ekkohno.anrex_back.entities.CategoryType;
import com.ekkohno.anrex_back.entities.Product;
import com.ekkohno.anrex_back.entities.Resources;
import com.ekkohno.anrex_back.exceptions.ResourceNotFoundEx;
import com.ekkohno.anrex_back.mapper.ProductMapper;
import com.ekkohno.anrex_back.repositories.ProductRepository;
import com.ekkohno.anrex_back.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product addProduct(ProductDto productDto) {
        Product product = productMapper.mapToProductEntity(productDto);
        return productRepository.save(product);
    }

    @Cacheable("products")
    @Override
    public List<ProductDto> getAllProducts(UUID categoryId, UUID typeId) {

        Specification<Product> productSpecification= Specification.where(null);

        if(null != categoryId){
            productSpecification = productSpecification.and(ProductSpecification.hasCategoryId(categoryId));
        }
        if(null != typeId){
            productSpecification = productSpecification.and(ProductSpecification.hasCategoryTypeId(typeId));
        }

        List<Product> products = productRepository.findAll(productSpecification);
        return productMapper.getProductDtos(products);
    }

    @Override
    public ProductDto getProductBySlug(String slug) {
        Product product= productRepository.findBySlug(slug);
        if(null == product){
            throw new ResourceNotFoundEx("Product Not Found!");
        }
        ProductDto productDto = productMapper.mapProductToDto(product);
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setCategoryTypeId(product.getCategoryType().getId());
        productDto.setProductResources(productMapper.mapProductResourcesListDto(product.getResources()));
        return productDto;
    }

    @Override
    public ProductDto getProductById(UUID id) {
        Product product= productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundEx("Product Not Found!"));
        ProductDto productDto = productMapper.mapProductToDto(product);
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setCategoryTypeId(product.getCategoryType().getId());
        productDto.setProductResources(productMapper.mapProductResourcesListDto(product.getResources()));
        return productDto;
    }

    @CacheEvict(value = "products", allEntries = true)
    @Override
    public Product updateProduct(ProductDto productDto) {
        Product product= productRepository.findById(productDto.getId()).orElseThrow(()-> new ResourceNotFoundEx("Product Not Found!"));
        return productRepository.save(productMapper.mapToProductEntity(productDto));
    }

    @Override
    public Product fetchProductById(UUID id) throws Exception {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Продукт не найден"));
    }

/*
    @Override
    public List<Product> addProducts(List<ProductDto> productDtos) {
        List<Product> products = productDtos.stream()
                .map(this::mapToProductEntity)
                .collect(Collectors.toList());

        return productRepository.saveAll(products);
    }

    private Product mapToProductEntity(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setMaterial(productDto.getMaterial());
        product.setSize(productDto.getSize());
        product.setCollection(productDto.getCollection());
        product.setNewArrival(productDto.isNewArrival());
        product.setPrice(productDto.getPrice());
        product.setRating(productDto.getRating());
        product.setSlug(productDto.getSlug());

        Category category = categoryService.getCategory(productDto.getCategoryId());
        if(null != category){
            product.setCategory(category);
            UUID categoryTypeId = productDto.getCategoryTypeId();
            CategoryType categoryType = category.getCategoryTypes().stream().filter(categoryType1 -> categoryType1.getId().equals(categoryTypeId)).findFirst().orElse(null);
            product.setCategoryType(categoryType);
        }
        if(null != productDto.getProductResources()){
            product.setResources(mapToProductResources(productDto.getProductResources(),product));
        }
        return productRepository.save(product);
    }

    private List<Resources> mapToProductResources(List<ProductResourceDto> productResources, Product product) {
        return productResources.stream().map(productResourceDto -> {
            Resources resources= new Resources();
            resources.setName(productResourceDto.getName());
            resources.setType(productResourceDto.getType());
            resources.setUrl(productResourceDto.getUrl());
            resources.setIsPrimary(productResourceDto.getIsPrimary());
            resources.setProduct(product);
            return resources; }).collect(Collectors.toList());
    }
*/


}
