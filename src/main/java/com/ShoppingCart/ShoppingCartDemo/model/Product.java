package com.ShoppingCart.ShoppingCartDemo.model;

import java.math.BigDecimal;
import java.util.List;

public class Product {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;

    private String description;
    private int inventory;
    
    private Category category;
    private List<Image> images;
    

}
