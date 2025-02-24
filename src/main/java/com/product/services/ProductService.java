package com.product.services;

import com.product.modal.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {


    //save
    public Long save(Product product);


    //Get All
    public List<Product>getAllProducts();


    // Get by id
    public Product getProductById(Long id);


    //Delete by id
    public  String deleteProductById(Long id);

    //update product
    public Product updateProduct(Long id,Product updatedProduct);


    void reduceQuantity(Long id, Long quantity);
}
