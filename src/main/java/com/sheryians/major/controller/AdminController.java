package com.sheryians.major.controller;

import com.sheryians.major.dto.ProductDto;
import com.sheryians.major.model.Categories;
import com.sheryians.major.model.Product;
import com.sheryians.major.service.CategoriesService;
import com.sheryians.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {

	@Autowired
	CategoriesService Categoriesservice;
	
	@Autowired
	ProductService Productservice;

	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}

	@GetMapping("/admin/categories")
	public String getCategories(Model model) {
		model.addAttribute("categories", Categoriesservice.getAllCategories());
		return "categories";
	}

	@GetMapping("/admin/categories/add")
	public String addCategories(Model model) {
		model.addAttribute("category", new Categories());
		return "categoriesAdd";
	}

	@PostMapping("/admin/categories/add")
	public String postCategoires(@ModelAttribute("category") Categories categories) {
		Categoriesservice.addCategories(categories);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategories(@PathVariable int id) {
		Categoriesservice.deleteCategorie(id);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/update/{id}")
	public String updateCategories(@PathVariable int id, Model model) {
		Optional<Categories> category = Categoriesservice.updateCategoriesById(id);
		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		} else {
			return "404";
		}
	}

	//Product section
	
	@GetMapping("/admin/products")
	public String getproduct(Model model) {
		model.addAttribute("products",Productservice.getAllProduct());
		return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String addProduct(Model model){
		model.addAttribute("productDTO", new ProductDto());
		model.addAttribute("categories", Categoriesservice.getAllCategories());
		return "productsAdd";
	}
	
	@PostMapping("/admin/products/add")
	public String postAddProduct(@ModelAttribute("ProductDTO") ProductDto productDto,
			@RequestParam("productImage") MultipartFile file,
			@RequestParam("imgName") String imgName) throws IOException{
		
		Product product = new Product();
		product.setId(productDto.getId());
		product.setName(productDto.getName());
		product.setCategories(Categoriesservice.updateCategoriesById(productDto.getCategoryId()).get());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setWeight(productDto.getWeight());
		String imageUUID;
		String uploadDir="";
		
		if(!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path filePathAndName = Paths.get(uploadDir,imageUUID);
			Files.write(filePathAndName,file.getBytes());
		}else {
			imageUUID=imgName;
		}
		product.setImageName(imageUUID);
		
		
//		Productservice.addProduct(productDto);
		return "redirect:/admin/products";
	}
	
	
}
