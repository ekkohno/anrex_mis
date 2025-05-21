package com.ekkohno.anrex_back.mapper;

import com.ekkohno.anrex_back.dto.ProductDto;
import com.ekkohno.anrex_back.dto.ProductResourceDto;
import com.ekkohno.anrex_back.entities.Category;
import com.ekkohno.anrex_back.entities.CategoryType;
import com.ekkohno.anrex_back.entities.Product;
import com.ekkohno.anrex_back.entities.Resources;
import com.ekkohno.anrex_back.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    private CategoryService categoryService;

    public Product mapToProductEntity(ProductDto productDto){
        Product product = new Product();
        if(null != productDto.getId()){
            product.setId(productDto.getId());
        }
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCollection(productDto.getCollection());
        product.setSize(productDto.getSize());
        product.setMaterial(productDto.getMaterial());
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



        return product;
    }

    private List<Resources> mapToProductResources(List<ProductResourceDto> productResources, Product product) {

        return productResources.stream().map(productResourceDto -> {
            Resources resources= new Resources();
            if(null != productResourceDto.getId()){
                resources.setId(productResourceDto.getId());
            }
            resources.setName(productResourceDto.getName());
            resources.setType(productResourceDto.getType());
            resources.setUrl(productResourceDto.getUrl());
            resources.setIsPrimary(productResourceDto.getIsPrimary());
            resources.setProduct(product);
            return resources;
        }).collect(Collectors.toList());
    }

    public List<ProductDto> getProductDtos(List<Product> products) {
        return products.stream().map(this::mapProductToDto).toList();
    }

    public ProductDto mapProductToDto(Product product) {

        return ProductDto.builder()
                .id(product.getId())
                .collection(product.getCollection())
                .name(product.getName())
                .material(product.getMaterial())
                .size(product.getSize())
                .price(product.getPrice())
                .isNewArrival(product.isNewArrival())
                .rating(product.getRating())
                .description(product.getDescription())
                .slug(product.getSlug())
                .categoryId(product.getCategory().getId())
                .categoryTypeId(product.getCategoryType().getId())
                .thumbnail(getProductThumbnail(product.getResources())).build();
    }

    private String getProductThumbnail(List<Resources> resources) {
        return resources.stream().filter(Resources::getIsPrimary).findFirst().orElse(null).getUrl();
    }

    public List<ProductResourceDto> mapProductResourcesListDto(List<Resources> resources) {
        return resources.stream().map(this::mapResourceToDto).toList();
    }

    private ProductResourceDto mapResourceToDto(Resources resources) {
        return ProductResourceDto.builder()
                .id(resources.getId())
                .url(resources.getUrl())
                .name(resources.getName())
                .isPrimary(resources.getIsPrimary())
                .type(resources.getType())
                .build();
    }
}