package com.amit.service;

import java.util.List;

import com.amit.entity.ContactDtls;

public interface ContactService {

	public ContactDtls Upsert(ContactDtls contact);

	public List<ContactDtls> getAllContact();

	public ContactDtls getById(Integer id);

	public String deleteById(Integer id);

}
