package com.cat.manager;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cat.dao.CustomerRepository;
import com.cat.dao.KittyRepository;
import com.cat.domain.Customer;
import com.cat.domain.Kitty;

@Component
public class KittyAutoBuyer {
	@Autowired
	private CustomerRepository customerRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private KittyRepository kittyRepository;

	public KittyAutoBuyer() {
		Thread thread = new Thread( new Runnable() {
			@Override
			public void run() {
				while(true) {
					try{
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						
					}

					autoBuye();
				}
			}
			
			@Transactional
			private void autoBuye() {
				Customer customer = customerRepository.findOne(1l);
				List<Kitty> kitties = kittyRepository.findAllByIsForSaleIsTrueOrderByCustomerDescIdDesc();
				Random random = new Random();
				logger.info("autoBuye");
				if (kitties.size() >= 100) {
					int overNumber = kitties.size() - 100;
					Set<Integer> randomSet = new HashSet<>();
					for (int i = 0; i < overNumber; i++) {
						randomSet.add(random.nextInt(kitties.size() - 1));
					}
					for (Integer seq : randomSet) {
						Kitty kitty = kitties.get(seq);
						Customer owner = kitty.getCustomer();
						if (owner != null) {
							owner.setWallet(new BigDecimal(owner.getWallet().doubleValue() + kitty.getPrice().doubleValue()));
							owner.removeKitties(kitty);
							customerRepository.save(owner);
						}
						kitty.setForSale(false);
						kitty.setCustomer(customer);
						kittyRepository.save(kitty);
						customer.addKitties(kitty);
						customerRepository.save(customer);
					}
				}else{
					int systemOwnKittiesNum = customer.getKitties().size();
					Set<Integer> randomSet = new HashSet<>();
					if(systemOwnKittiesNum < 50){
						for (int i = 0; i < systemOwnKittiesNum/2; i++) {
							randomSet.add(random.nextInt(systemOwnKittiesNum - 1));
						}
					}else{
						for (int i = 0; i < 50; i++) {
							randomSet.add(random.nextInt(systemOwnKittiesNum - 1));
						}
					}
					for(Integer seq : randomSet){
						Kitty kitty = customer.getKitties().get(seq);
						customer.removeKitties(kitty);
						customerRepository.save(customer);
						kitty.setCustomer(null);
						kitty.setForSale(true);
						kitty.setPrice(new BigDecimal(200));
						kittyRepository.save(kitty);
					}
				}
			}
		});
		thread.setDaemon(true);
		thread.setName("autoBuyer");
		thread.start();
	}
	
}
