package com.amit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.entity.ContactDtls;
import com.amit.repository.ContectRepository;
import com.amit.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContectRepository service;

	@Override
	public ContactDtls Upsert(ContactDtls contact) {
		ContactDtls save = service.save(contact);
		return save;
	}

	@Override
	public List<ContactDtls> getAllContact() {
		return service.findAll();
	}

	@Override
	public ContactDtls getById(Integer id) {
		Optional<ContactDtls> findById = service.findById(id);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public String deleteById(Integer id) {
		if (service.existsById(id)) {
			service.deleteById(id);
			return "delete Success";
		} else {
			return "no record found";
		}
	}
}
