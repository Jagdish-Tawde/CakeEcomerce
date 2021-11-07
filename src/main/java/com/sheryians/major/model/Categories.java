package com.sheryians.major.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Categories {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "categorie_id")
	public int id;
	
	public String name;
	
}
