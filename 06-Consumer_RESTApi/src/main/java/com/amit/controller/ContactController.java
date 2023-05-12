package com.amit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amit.request.ContactRequ;
import com.amit.response.ContactResp;
import com.amit.service.impl.ContactServiceImpl;

@Controller
public class ContactController {

	@Autowired
	private ContactServiceImpl contactImpl;

	@Autowired
	private HttpSession session;

	@GetMapping("/")
	public String loadIndex(Model model) {
		model.addAttribute("contact", new ContactRequ());
		return "index";
	}

	@PostMapping("/contact")
	public String createContact(ContactRequ contact, Model model) {

		Integer contactId = (Integer) session.getAttribute("contactId");
		if (contactId != null) {
			ContactResp update = contactImpl.Update(contact, contactId);
			model.addAttribute("msg", "Updation Successfuly");
			session.invalidate();
		} else {
			ContactResp contactResp = contactImpl.Upsert(contact);
			if (contactResp != null) {
				model.addAttribute("msg", "Data inserted Successfuly");
			}
		}
		model.addAttribute("contact", new ContactRequ());
		return "index";
	}

	@GetMapping("/contacts")
	public String getContacts(Model model) {
		List<ContactResp> allContact = contactImpl.getAllContact();
		model.addAttribute("contacts", allContact);
		return "data";
	}

	@GetMapping("/viewcontact")
	public String view(Model model) {
		model.addAttribute("contact", new ContactResp());
		return "getbyid";
	}

	@GetMapping("/contactid")
	public String viewById(@RequestParam("id") Integer id, Model model) {
		ContactResp byId = contactImpl.getById(id);
		model.addAttribute("contact", byId);
		return "getbyid";
	}

	@GetMapping("/edit")
	public String update(@RequestParam("id") Integer id, Model model) {
		ContactResp byId = contactImpl.getById(id);
		session.setAttribute("contactId", id);
		model.addAttribute("contact", byId);
		return "index";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") Integer id, Model model) {
		contactImpl.deleteById(id);
		return "redirect:/contacts";
	}
}