package com.sheryians.major.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheryians.major.dto.ProductDto;
import com.sheryians.major.model.Product;
import com.sheryians.major.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productrepository;
	
	public List<Product> getAllProduct(){
		return productrepository.findAll();
	}
	
	public void addProduct(Product product) {
		productrepository.save(product);
	}
}
