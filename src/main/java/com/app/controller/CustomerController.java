package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.model.Customer;
import com.app.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerService service;

	//1.Display register page with Form backing Object
	@RequestMapping("/reg")
	public String showPage(Model map) {
		// Form backing Object
		map.addAttribute("customer",new Customer());

		return "Register";
	}

	//2. On clicking submit read form data and save  into DB
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveData(
			@ModelAttribute Customer customer,Model map
			) {
		//Insert into db
		Integer id=service.saveCustomer(customer);
		map.addAttribute("message","customer '"+id+"' created");
		//clearing form data
		map.addAttribute("customer",new Customer());
		return "Register";
	}

	//Display all records in UI
	public String showAllPage(Model map) {
		//fetch data from DB
		List<Customer> cobs=service.getAllCustomers();
		//send  data to UI
		map.addAttribute("list",cobs);
		return "Data";
		
	}
	

	//4.fetch data based on Id & display
	@RequestMapping("/view/{id}")
	public String viewOne(
			@PathVariable Integer id, Model map
			) {
		
		Customer c= service.getOneCustomer(id);
		
		map.addAttribute("ob",c);
		return "View";
		
	}
	
	//5.delete rows Based on Id
	public String deleteOne(
			@PathVariable Integer id,Model map
			) {
		service.deleteCustomer(id);
		//fetch all new data
		List<Customer> cobs=service.getAllCustomers();
		//send  data to UI
		map.addAttribute("list",cobs);
		//diplay message
		map.addAttribute("message", "customer '"+id+"' created ");
		return "Data";
		
	}
	

}
