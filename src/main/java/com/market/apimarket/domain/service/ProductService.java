package com.market.apimarket.domain.service;

import com.market.apimarket.domain.Product;
import com.market.apimarket.domain.repository.ProductRepository;
import com.market.apimarket.persistence.crud.ProductoCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }
    public Optional<Product> getProduct(int productId){
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    }

    public Product saveProducto(Product product){
        return productRepository.saveProducto(product);
    }
    public boolean deleteProducto(int productId){

        return getProduct(productId).map(product -> {
            productRepository.deleteProducto(productId);
            return true;
        }).orElse(false);
    }
}
