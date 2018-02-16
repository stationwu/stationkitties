package com.cat.web.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;


import org.apache.batik.transcoder.TranscoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cat.dao.CustomerRepository;
import com.cat.dao.KittyRepository;
import com.cat.domain.Customer;
import com.cat.domain.Kitty;
import com.cat.domain.dto.CustomerContainer;
import com.cat.domain.dto.KittyContainer;
import com.cat.service.KittyService;

@RestController
public class CustomerController {
	public static final String PATH = "/User";
	
	public static final String USER_PATH = PATH + "/{openCode}";
	
	@Autowired
	private KittyService kittyService;
	
	@Autowired
	private KittyRepository kittyRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@RequestMapping(path = USER_PATH, method = RequestMethod.GET)
	@Transactional
	public List<KittyContainer> getKitty(@PathVariable String openCode, HttpSession session) throws IOException, TranscoderException {
		Customer customer = null;
		if(!customerRepository.isCustomerAlreadyRegistered(openCode)){
			customer = new Customer(openCode);
			Kitty kitty = kittyService.getRaddomKitty();
			customer.addKitties(kitty);
			customer = customerRepository.save(customer);
			kitty.setCustomer(customer);
			kittyRepository.save(kitty);
		}else{
			customer = customerRepository.findOneByOpenCode(openCode);
		}
		session.setAttribute("OpenCode", openCode);
		CustomerContainer customerContainer = new CustomerContainer(customer);
		return customerContainer.getKitties();
	}


}
