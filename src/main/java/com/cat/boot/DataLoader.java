package com.cat.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cat.dao.KittyRepository;
import com.cat.service.KittyService;

@Component
public class DataLoader implements CommandLineRunner {

	@Autowired
	private KittyService kittyService;
	
	@Autowired
	private KittyRepository kittyRepository;
	
	@Override
	public void run(String... args) throws Exception {
		if (0 == kittyRepository.count()) {
			for(int i=0;i<100;i++){
				kittyService.getRaddomKitty();
			}
		}
	}
}