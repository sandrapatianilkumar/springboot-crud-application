package com.demo.crud.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.crud.example.entity.Product;
import com.demo.crud.example.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepo;

	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}

	public List<Product> saveProducts(List<Product> products) {
		return productRepo.saveAll(products);
	}

	public List<Product> getProducts() {
		return productRepo.findAll();
	}

	public Product getProductById(Integer id) {
		return productRepo.findById(id).orElse(null);
	}

	public Product getProductByName(String name) {
		return productRepo.findByName(name);
	}

	public String deleteProduct(Integer id) {
		productRepo.deleteById(id);
		return "Product removed!! " + id;
	}

	public Product updateProduct(Product product) {
		Product existingProduct = productRepo.findById(product.getId()).orElse(null);
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setQuantity(product.getQuantity());
		return productRepo.save(existingProduct);
	}

}
