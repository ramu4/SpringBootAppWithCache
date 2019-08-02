package com.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Customer;
import com.app.repo.CustomerReposotiry;
import com.app.service.ICustomerService;
@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerReposotiry repo;
	
	@Transactional
	public Integer saveCustomer(Customer c) {
	
		/*
		Customer c1=repo.save(c);
		Integer id=c1.getCustId();
		return id;*/
		//above code equal to below
		return repo.save(c).getCustId();
	}

	@Transactional(readOnly=true)
	public List<Customer> getAllCustomers() {
		
		return repo.findAll();
	}

	@Transactional(readOnly=true)
	public Customer getOneCustomer(Integer custId) {
	
		Optional<Customer> c=repo.findById(custId);
		if (c.isPresent()) {
			return c.get();
			
		}
		
		return null;
	}

	@Transactional
	public void deleteCustomer(Integer custId) {
		repo.deleteById(custId);
	}

}
