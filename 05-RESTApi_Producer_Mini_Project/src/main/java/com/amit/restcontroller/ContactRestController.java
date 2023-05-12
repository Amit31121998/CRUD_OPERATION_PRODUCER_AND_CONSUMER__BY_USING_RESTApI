package com.amit.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amit.entity.ContactDtls;
import com.amit.service.impl.ContactServiceImpl;

@RestController
public class ContactRestController {

	@Autowired
	private ContactServiceImpl serviceImpl;

	 @PostMapping(value = "/contact", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ContactDtls> createContect(@RequestBody ContactDtls contact) {
		ContactDtls upsert = serviceImpl.Upsert(contact);
		return new ResponseEntity<>(upsert, HttpStatus.CREATED);
	}

	@GetMapping("/contact/{id}")
	public ResponseEntity<ContactDtls> getContact(@PathVariable Integer id) {
		ContactDtls contact = serviceImpl.getById(id);
		return new ResponseEntity<>(contact, HttpStatus.OK);
	}

	@GetMapping("/contacts")
	public ResponseEntity<List<ContactDtls>> getAllContact() {
		List<ContactDtls> allContact = serviceImpl.getAllContact();
		return new ResponseEntity<>(allContact, HttpStatus.OK);
	}

	@GetMapping(value= "/delete/{id}")
	public ResponseEntity<String> deleteContact(@PathVariable Integer id) {
		  String deleteById = serviceImpl.deleteById(id);
		return new ResponseEntity<>(deleteById, HttpStatus.OK);
	}
	
	@PutMapping("/contact")
	public ResponseEntity<ContactDtls> updateContect(@RequestBody ContactDtls contact) {
		 ContactDtls upsert = serviceImpl.Upsert(contact);
		
		return new ResponseEntity<>(upsert, HttpStatus.CREATED);
	}
}