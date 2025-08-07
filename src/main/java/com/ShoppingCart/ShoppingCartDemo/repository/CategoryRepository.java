package com.ShoppingCart.ShoppingCartDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ShoppingCart.ShoppingCartDemo.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
