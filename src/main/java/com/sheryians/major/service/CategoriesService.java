package com.sheryians.major.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheryians.major.model.Categories;
import com.sheryians.major.repository.CategoriesRepository;

@Service
public class CategoriesService {

	@Autowired
	CategoriesRepository categoriesRepository;
	
	public List<Categories> getAllCategories(){
		
		return categoriesRepository.findAll();
	}
	
	public void addCategories(Categories categories) {
		categoriesRepository.save(categories);
	}
	
	public void deleteCategorie(int id) {
		categoriesRepository.deleteById(id);
	}
	
	public Optional<Categories> updateCategoriesById(int id){
		return categoriesRepository.findById(id);
	}
}
