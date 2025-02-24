package com.product.serviceImpl;

import com.product.Exceptions.ProductServiceCustomException;
import com.product.Exceptions.ResourceNotFoundException;
import com.product.modal.Product;
import com.product.repository.ProductRepository;
import com.product.services.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;



    @Override
    public Long save(Product product) {
        log.info("Adding Product: "+product);

        Product data= Product.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();

        productRepository.save(data);
        return data.getProductId();
    }

    @Override
    public List<Product> getAllProducts() {
         return productRepository.findAll();
    }

    @Override
    public  Product  getProductById(Long id) {
       Product data= null;
        try {
            data = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product Not Found with given id: "+id));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        return  data;
    }

    @Override
    public String deleteProductById(Long id) {
//        Check
     List<Long>data= productRepository.findById(id).stream().map(e-> e.getProductId()).collect(Collectors.toList());

      if (data != null  ){
           productRepository.deleteById(id);
    return  "Data Deleted Successfully...";
      }else{
          return "Something wend wrong!!";
      }



    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {

        try {
            return productRepository.findById(id).map(existingDb-> {
                existingDb.setProductName(updatedProduct.getProductName());
                existingDb.setPrice(updatedProduct.getPrice());
                existingDb.setQuantity(updatedProduct.getQuantity());
                return  productRepository.save(existingDb);
            }).orElseThrow(()-> new ResourceNotFoundException("Product Not Found with given id: "+id));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void reduceQuantity(Long id, Long quantity) {
        log.info("Reduce Quantity {} for Id : {}",quantity,id);


            Product product=productRepository.findById(id)
                    .orElseThrow(()-> new ProductServiceCustomException("Product with Given id is not found: "+id));

            if (product.getQuantity()< quantity){
                log.error("Insufficient quantity for product ID: {}",id);
                throw  new ProductServiceCustomException("Product does not have sufficient quantity: "+id);
            }

            product.setQuantity(product.getQuantity()- quantity);
            productRepository.save(product);

            log.info("Product Quantity Updated Successfully..");


    }





}
