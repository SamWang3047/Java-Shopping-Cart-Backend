package com.ShoppingCart.ShoppingCartDemo.service.product;

import java.util.List;

import com.ShoppingCart.ShoppingCartDemo.model.Product;
import com.ShoppingCart.ShoppingCartDemo.repository.ProductRepository;
import com.ShoppingCart.exception.ProductNotFoundException;

public class ProductService implements IProductService {
    private ProductRepository productRepository;

    // Implement the methods defined in IProductService interface
    @Override
    public Product addProduct(Product product) {
        // Logic to add product
        return null; // Placeholder return
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
