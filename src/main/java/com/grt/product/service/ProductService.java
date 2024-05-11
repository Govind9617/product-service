package com.grt.product.service;

import com.grt.product.Repo.ProductRepo;
import com.grt.product.dto.ProductRequest;
import com.grt.product.dto.ProductResponse;
import com.grt.product.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepo productRepo;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder().
                name(productRequest.name())
                .Description(productRequest.description())
                .price(productRequest.price()).build();

        productRepo.save(product);
        log.info("product created succefully");
        return new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice());

    }

    public List<ProductResponse> getAllProduct() {
        return productRepo.findAll().stream().
                map(product -> new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice())).toList();
    }
}
