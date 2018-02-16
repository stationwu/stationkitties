package com.cat.web.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.batik.transcoder.TranscoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cat.dao.CustomerRepository;
import com.cat.domain.Customer;
import com.cat.domain.Kitty;
import com.cat.domain.dto.CustomerContainer;
import com.cat.domain.dto.KittyContainer;
import com.cat.service.KittyService;

@RestController
@ExposesResourceFor(Kitty.class)
public class KittyController {
	public static final String PATH = "/Kitty";
	
	public static final String 	KITTY_ADD_PATH = PATH +"/Add";
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private KittyService kittyService;
	
	@RequestMapping(path = KITTY_ADD_PATH, method = RequestMethod.GET)
	@Transactional
	public List<KittyContainer> addKittyToCustomer(HttpSession session) throws IOException, TranscoderException {
		String openCode = (String) session.getAttribute("OpenCode");
		Customer customer = customerRepository.findOneByOpenCode(openCode);
		customer.addKitties(kittyService.getRaddomKitty());
		customerRepository.save(customer);
		CustomerContainer customerContainer = new CustomerContainer(customer);
		return customerContainer.getKitties();
	}

}

