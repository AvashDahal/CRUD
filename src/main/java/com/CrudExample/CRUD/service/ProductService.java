package com.CrudExample.CRUD.service;

import com.CrudExample.CRUD.entity.Product;
import com.CrudExample.CRUD.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService
{
    @Autowired
    ProductRepository productRepository;

    public void saveProduct(Product product)
    {
        productRepository.save(product);
    }
    public List<Product> getProducts()
    {
        return productRepository.findAll();
    }

    public Product getProductById(int id)
    {

            Optional<Product> optionalProduct=productRepository.findById(id);//Maile productObject banai banai search garnu parena,use inbuilt function findByID(id)
        if (optionalProduct.isPresent())
        {
            Product product = optionalProduct.get();
            return product;
        }
        else
        {
            throw new RuntimeException("The Product is not found");
        }
    }
    public Product getProductByname(String name) {
        Optional<Product> optionalProduct = productRepository.findByNameIgnoreCase(name);
        if (optionalProduct.isPresent())
        {
            Product product = optionalProduct.get();

            return product ;
        }
        else
        {
            throw new RuntimeException("The product of product name+"+name+"is not found");
        }
    }
    public String deleteProductById(int id)
    {
        productRepository.deleteById(id);
        return "The Product : "+id+"is removed";
    }
    public Product updateProduct(int id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {

            Product existingProduct=optionalProduct.get();
            existingProduct.setName(product.getName());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setPrice(product.getPrice());
            return productRepository.save(existingProduct);
        }
        else
        {
            throw new RuntimeException("The Product is not found");
        }
    }


}
