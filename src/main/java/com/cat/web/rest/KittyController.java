package com.cat.web.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.ExposesResourceFor;
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
@ExposesResourceFor(Kitty.class)
public class KittyController {
	public static final String PATH = "/Kitty";
	
	public static final String KITTY_PATH = PATH + "/{id}";

	public static final String MARKET_PATH = "/Market";
	
	public static final String SALE_PATH = "/Sale";
	
	public static final String PURCHASE_PATH = "/Purchase";
	
	public static final String SALE_CANCEL_PATH = "/SaleCancel";

	public static final String KITTY_ADD_PATH = PATH + "/Add";

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private KittyRepository kittyRepository;

	@Autowired
	private KittyService kittyService;

	@RequestMapping(path = KITTY_ADD_PATH, method = RequestMethod.GET)
	@Transactional
	public List<KittyContainer> addKittyToCustomer(HttpSession session) throws IOException, TranscoderException {
		String openCode = (String) session.getAttribute("OpenCode");
		Customer customer = customerRepository.findOneByOpenCode(openCode);
		Kitty kitty = kittyService.getRaddomKitty();
		kitty.setCustomer(customer);
		customer.addKitties(kittyRepository.save(kitty));
		CustomerContainer customerContainer = new CustomerContainer(customerRepository.save(customer));
		return customerContainer.getKitties();
	}
	
	@RequestMapping(path = MARKET_PATH, method = RequestMethod.GET)
	@Transactional
	public List<KittyContainer> getKittyMarket(Pageable pageRequest,HttpSession session) throws IOException, TranscoderException {
		Page<Kitty> page = kittyRepository.findAllByIsForSaleIsTrueOrderByCustomerDescIdDesc(pageRequest);
		//List<Kitty> kitties = kittyRepository.findAllByCustomerIsNull();
		ArrayList<KittyContainer> kittyContainers = page.getContent().stream().map(x -> new KittyContainer(x))
				.sorted(Comparator.comparing(KittyContainer::getId)).collect(Collectors.toCollection(ArrayList::new));
		return kittyContainers;
	}
	
	@RequestMapping(path = KITTY_PATH, method = RequestMethod.GET)
	@Transactional
	public KittyContainer getKittyById(@PathVariable Long id) throws IOException, TranscoderException {
		Kitty kitty = kittyRepository.findOne(id);
		return new KittyContainer(kitty);
	}
	
	@RequestMapping(path = SALE_PATH, method = RequestMethod.GET)
	@Transactional
	public KittyContainer saleKitty(@Param("openCode") String openCode,@Param("kittyId") Long kittyId,@Param("price") Double price) throws Exception {
		Kitty kitty = kittyRepository.findOne(kittyId);
		Customer customer = customerRepository.findOneByOpenCode(openCode);
		if(customer == null)
			throw new Exception("no customer");
		kitty.setForSale(true);
		kitty.setPrice(new BigDecimal(price));
		return new KittyContainer(kittyRepository.save(kitty));
	}
	
	@RequestMapping(path = PURCHASE_PATH, method = RequestMethod.GET)
	@Transactional
	public String purchaseKitty(@Param("openCode") String openCode,@Param("kittyId") Long kittyId) throws Exception {
		Kitty kitty = kittyRepository.findOne(kittyId);
		Customer customer = customerRepository.findOneByOpenCode(openCode);
		if(customer == null){
			throw new Exception("no customer");
		}
		if(customer.getWallet().doubleValue() < kitty.getPrice().doubleValue()){
			throw new Exception("鱼干不足");
		}
		if(customer.getKitties().size()>9){
			throw new Exception("猫窝中的猫猫数量过多，最多只能养9只猫~");
		}
		Customer owner = kitty.getCustomer();
		if(owner != null){
			owner.setWallet(new BigDecimal(owner.getWallet().doubleValue() + kitty.getPrice().doubleValue()));
			owner.removeKitties(kitty);
			customerRepository.save(owner);
		}
		kitty.setForSale(false);
		kitty.setCustomer(customer);
		kittyRepository.save(kitty);
		customer.setWallet(new BigDecimal(customer.getWallet().doubleValue() - kitty.getPrice().doubleValue()));
		customer.addKitties(kitty);
		customerRepository.save(customer);
		return "购买成功";
	}
	
	@RequestMapping(path = SALE_CANCEL_PATH, method = RequestMethod.GET)
	@Transactional
	public KittyContainer saleCancelKitty(@Param("openCode") String openCode,@Param("kittyId") Long kittyId) throws Exception {
		Kitty kitty = kittyRepository.findOne(kittyId);
		Customer customer = customerRepository.findOneByOpenCode(openCode);
		if(customer == null)
			throw new Exception("no customer");
		if(kitty.isForSale() == false){
			throw new Exception("已经被买走咯~");
		}
		kitty.setForSale(false);
		return new KittyContainer(kittyRepository.save(kitty));
	}

}
