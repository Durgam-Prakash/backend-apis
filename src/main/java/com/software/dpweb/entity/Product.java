package com.software.dpweb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String code;

	
	private String discription;
	
	private String unit;

	private String caseNumber;

	private String analysis;
	
	private String accurate;
	
	private String aqua;
	
	private String again;

	@Column(name = "all_value")
	private String allValue;
	
	private String acids;

}
