package com.CrudExample.CRUD.controller;

import com.CrudExample.CRUD.entity.Product;
import com.CrudExample.CRUD.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController
{
    @Autowired
    private ProductService productService;


    @PostMapping("/create")
    public ResponseEntity<String> saveProduct (@RequestBody Product product)
    {
        productService.saveProduct(product);
        return ResponseEntity.ok("The product is added successfully");


    }
    @GetMapping
    public ResponseEntity<List<Product>> getProducts()
    {
      List<Product> product= productService.getProducts();
       return ResponseEntity.ok(product);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id)
    {
       Product product=  productService.getProductById(id);
       return ResponseEntity.ok(product);
    }
    @GetMapping("/byName/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name)
    {
        Product product= productService.getProductByname(name);
        return ResponseEntity.ok(product);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable int id)
    {
        productService.deleteProductById(id);
        return ResponseEntity.ok("The Product is deleted successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestBody Product product)
    {

        Product updated= productService.updateProduct(id, product);
        if (updated!=null) {
            return ResponseEntity.ok("The product is updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update product");
        }

    }


}
