package com.software.dpweb.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	private String name;

	@Column(name = "email", unique = true)
	private String email;

	private String password;

	private String mobile;
	
	private String passwordResetKey;

	private LocalDateTime createdOn = LocalDateTime.now();
	private Boolean isActive = true;
	private Boolean isEmailVerified = false;

	
	
	private String accountNumber;
	private Double balance;
}
