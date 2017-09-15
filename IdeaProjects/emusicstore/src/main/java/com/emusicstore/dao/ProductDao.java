package com.emusicstore.dao;

import com.emusicstore.model.Product;

import java.util.List;

public interface ProductDao {

    void addProduct(Product product);
    void editProduct(Product product);
     Product getProductById(String id);
     List<Product> getAllproduct();
     void deleteProduct(String productId);
}
