package com.ShoppingCart.ShoppingCartDemo.service.product;

import java.util.List;

import com.ShoppingCart.ShoppingCartDemo.model.Product;
import com.ShoppingCart.ShoppingCartDemo.request.AddProductRequest;

public interface IProductService {
    Product addProduct(AddProductRequest request);
    Product getProductById(Long id);
    void deleteProduct(Long id);
    void updateProduct(Long id, Product product);

    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductsByBrandAndName(String brand, String name);
}
