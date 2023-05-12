package com.amit.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.amit.request.ContactRequ;
import com.amit.response.ContactResp;
import com.amit.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	private String CREATE_CONTACT_URL = "http://localhost:9090/contact";

	private String GET_CONTACT_URL = "http://localhost:9090/contact/{id}";

	int port = 9090;
	String GETALL_CONTACT_URL = String.format("http://localhost:%d/contacts", port);

	private String DELETE_CONTACT_URL = "http://localhost:9090/delete/{id}";


	@Override
	public ContactResp Upsert(ContactRequ contact) {

		try {
			WebClient webClient = WebClient.create();
			ContactResp contactRespo = webClient.post()
					.uri(CREATE_CONTACT_URL).bodyValue(contact)
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) // Specify expected content type
					.retrieve().bodyToMono(ContactResp.class).block();
			return contactRespo;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ContactResp> getAllContact() {
		try {
			WebClient webClient = WebClient.create();
			List<ContactResp> list = webClient.get().uri(GETALL_CONTACT_URL).retrieve().bodyToFlux(ContactResp.class)
					.collectList().block();
			return (List<ContactResp>) list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ContactResp getById(Integer id) {

		WebClient webClient = WebClient.create();

		ContactResp resp = webClient
				.get().uri(GET_CONTACT_URL, id)
				.retrieve()
				.bodyToMono(ContactResp.class)
				.block();

		return resp;
	}

	@Override
	public String deleteById(Integer id) {
	    WebClient webClient = WebClient.create();
	    webClient.get().uri(DELETE_CONTACT_URL, id).retrieve().toBodilessEntity().block();
	    return "success";
	}

	@Override
	public ContactResp Update(ContactRequ contact,Integer id) {
		
		ContactResp res=new ContactResp();
		BeanUtils.copyProperties(contact, res);
		res.setId(id);
		try {
			WebClient webClient = WebClient.create();
			ContactResp contactRespo = webClient.post().uri(CREATE_CONTACT_URL).bodyValue(res)
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) // Specify expected content type
					.retrieve().bodyToMono(ContactResp.class).block();
			return contactRespo;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
