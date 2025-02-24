package com.product.controller;

import com.product.modal.Product;
import com.product.serviceImpl.ProductServiceImpl;
import com.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping
    ResponseEntity<?>save(@RequestBody Product product){
        if (product != null){
             Long data= productService.save(product);
             return  new ResponseEntity<>(data, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    ResponseEntity<?>getAll(){

        List<Product> data=productService.getAllProducts()   ;
        return  new ResponseEntity<>(data,HttpStatus.OK);
    }


    @GetMapping("{id}")
    ResponseEntity<Product>findProductById(@PathVariable Long id){
    if (id !=null){
        Product product=productService.getProductById(id);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }

    @DeleteMapping("{id}")
    ResponseEntity<?>deleteProduct(@PathVariable Long id){
        if (id!=null){
           String data=  productService.deleteProductById(id);
           return  new ResponseEntity<>(data,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("{id}")
    ResponseEntity<?>updateProdcut(@PathVariable Long id,@RequestBody Product updatedProduct){

        if(id !=null && updatedProduct !=null){
         Product data=   productService.updateProduct(id,updatedProduct);
         return new ResponseEntity<>(data,HttpStatus.OK);

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void>reduceQuantity(@PathVariable Long id ,@RequestParam Long quantity  ){
        productService.reduceQuantity(id,quantity);

        return  new ResponseEntity<>(HttpStatus.OK);
    }










}
