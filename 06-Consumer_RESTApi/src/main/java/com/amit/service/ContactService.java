package com.amit.service;

import java.util.List;

import com.amit.request.ContactRequ;
import com.amit.response.ContactResp;

public interface ContactService {
	

	public List<ContactResp> getAllContact();

	public ContactResp getById(Integer id);

	public String deleteById(Integer id);


	ContactResp Upsert(ContactRequ contact);

	ContactResp Update(ContactRequ contact, Integer id);

}