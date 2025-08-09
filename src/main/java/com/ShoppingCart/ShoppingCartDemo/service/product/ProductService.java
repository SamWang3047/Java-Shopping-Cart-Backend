package com.ShoppingCart.ShoppingCartDemo.service.product;

import java.util.List;
import java.util.Optional;


import com.ShoppingCart.ShoppingCartDemo.exception.ProductNotFoundException;
import com.ShoppingCart.ShoppingCartDemo.model.Category;
import com.ShoppingCart.ShoppingCartDemo.model.Product;
import com.ShoppingCart.ShoppingCartDemo.repository.CategoryRepository;
import com.ShoppingCart.ShoppingCartDemo.repository.ProductRepository;
import com.ShoppingCart.ShoppingCartDemo.request.AddProductRequest;
import com.ShoppingCart.ShoppingCartDemo.request.ProductUpdateRequest;

public class ProductService implements IProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    
    @Override
    public Product addProduct(AddProductRequest request) {
        // check if the catefory if found in the DB
        // If yes, set it as the new product's category
        // If No, then save it as a new category
        // The set as the new product category
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        
        request.setCategory(category);
        return createProduct(request, category);
    }

    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
            request.getName(),
            request.getBrand(),
            request.getPrice(),
            request.getDescription(),
            request.getInventory(),
            category
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete, () -> {
                    throw new ProductNotFoundException("Product not found with id: " + id);
                });
    }

    @Override
    public void updateProduct(Long id, Product product) {
        productRepository.findById(id).ifPresent(existingProduct -> {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setBrand(product.getBrand());
            productRepository.save(existingProduct);
        });
    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setCategory(request.getCategory());
        existingProduct.setBrand(request.getBrand());
        return productRepository.save(existingProduct);
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }


    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand); 
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }


    
}
