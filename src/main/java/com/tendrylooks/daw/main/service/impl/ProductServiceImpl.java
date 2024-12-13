package com.tendrylooks.daw.main.service.impl;

import com.tendrylooks.daw.main.dto.ProductCreateDto;
import com.tendrylooks.daw.main.dto.ProductDetailDto;
import com.tendrylooks.daw.main.dto.ProductDto;
import com.tendrylooks.daw.main.entity.Product;
import com.tendrylooks.daw.main.repository.ProductRepository;
import com.tendrylooks.daw.main.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createProduct(ProductCreateDto productCreateDto) {
        productRepository.createProduct(
                productCreateDto.nomProd(),
                productCreateDto.descProd(),
                productCreateDto.codCat(),
                productCreateDto.preProd(),
                productCreateDto.stockProd(),
                productCreateDto.imgProd()
        );
    }

    @Override
    public void updateProduct(ProductDetailDto productDetailDto) {
        Product existingProduct = productRepository.findById(productDetailDto.codProd())
                .orElseThrow(() -> new EntityNotFoundException("Product not found for ID: " + productDetailDto.codProd()));

        if (productDetailDto.nomProd() != null) {
            existingProduct.setNomProd(productDetailDto.nomProd());
        }
        if (productDetailDto.descProd() != null) {
            existingProduct.setDescProd(productDetailDto.descProd());
        }
//        if (productDetailDto.codCat() != null) {
//            Category category = categoryRepository.findById(productDetailDto.codCat())
//                    .orElseThrow(() -> new EntityNotFoundException("Category not found for ID: " + productDetailDto.codCat()));
//            existingProduct.setCategory(category); // Set the category object
//        }
        if (productDetailDto.preProd() != null) {
            existingProduct.setPreProd(productDetailDto.preProd());
        }
        if (productDetailDto.stockProd() != null) {
            existingProduct.setStockProd(productDetailDto.stockProd());
        }
        if (productDetailDto.imgProd() != null) {
            existingProduct.setImgProd(productDetailDto.imgProd());
        }
        if (productDetailDto.estProd() != null) {
            existingProduct.setEstProd(productDetailDto.estProd());
        }
        if (productDetailDto.fecProd() != null) {
            existingProduct.setFecProd(productDetailDto.fecProd());
        }

        productRepository.save(existingProduct);
    }

    @Override
    public ProductDetailDto findById(Integer codProd) {
        Product product = productRepository.findById(codProd)
                .orElseThrow(() -> new EntityNotFoundException("Product not found for ID: " + codProd));

        return new ProductDetailDto(
                product.getCodProd(),
                product.getNomProd(),
                product.getDescProd(),
                product.getCategory().getCodCat(),
                product.getPreProd(),
                product.getStockProd(),
                product.getImgProd(),
                product.getEstProd(),
                product.getFecProd()
        );
    }

    @Override
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        int limit = pageable.getPageSize();
        int offset = (int) pageable.getOffset();

        List<Product> products = productRepository.spGetAllProducts(limit, offset);

        List<ProductDto> productDtos = products.stream().map(this::convertToDto).collect(Collectors.toList());

        long totalProducts = productRepository.count();

        return new PageImpl<>(productDtos, pageable, totalProducts);
    }

    private ProductDto convertToDto(Product product) {
        return new ProductDto(
                product.getCodProd(),
                product.getNomProd(),
                product.getPreProd(),
                product.getStockProd(),
                product.getEstProd()
        );
    }
}
