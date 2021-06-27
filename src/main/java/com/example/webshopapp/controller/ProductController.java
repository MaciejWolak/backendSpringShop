package com.example.webshopapp.controller;

import com.example.webshopapp.model.Product;
import com.example.webshopapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getAll();
        if (CollectionUtils.isEmpty(products)) {
            throw new EntityNotFoundException();
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/api/products")
    public ResponseEntity<Product> createNewProduct(@Valid @RequestBody Product product) throws IOException {

        Product persistedProduct = productService.createNew(product);
        return new ResponseEntity<>(persistedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id) {
        Product product = productService.getOne(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/api/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @Valid @RequestBody Product product) {
        Product newProduct = productService.getOne(id);
        newProduct.setName(product.getName());
        newProduct.setQuantity(product.getQuantity());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setImageUrl(product.getImageUrl());
        productService.update(newProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @GetMapping("/sumq")
    public Long sumQuantities(){
        return productService.sunQuantities();
    }
    @GetMapping("/total")
    public Double total(){
        return productService.total();
    }

}
