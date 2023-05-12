 package com.amit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ContactDtls {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String email;
	private Long phno;
}
