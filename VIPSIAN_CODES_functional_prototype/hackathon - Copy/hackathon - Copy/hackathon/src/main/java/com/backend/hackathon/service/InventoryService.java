package com.backend.hackathon.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.hackathon.entity.Product;
import com.backend.hackathon.repo.ProductRepository;

@Service
public class InventoryService {

	
	    @Autowired
	    private ProductRepository productRepository;
	    
	    public Product addProduct(Product product) {
	        return productRepository.save(product);
	    }

	    public void updateStock(Long productId, int quantity) throws Exception {
	        Product product = productRepository.findById(productId)
	                          .orElseThrow(() -> new Exception("Product not found!"));
	        product.setQuantity(product.getQuantity() + quantity);
	        productRepository.save(product);
	    }

	    public List<Product> getLowStockProducts(int threshold) {
	        return productRepository.findAll()
	                .stream()
	                .filter(product -> product.getQuantity() < threshold)
	                .collect(Collectors.toList());
	    }
	
//	    public void deleteProduct(Long productId) {
//	    
//	    }
	
}
