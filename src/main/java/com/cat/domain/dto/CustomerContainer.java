package com.cat.domain.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.cat.domain.Customer;
import com.cat.domain.Kitty;

import lombok.Data;

@Data
public class CustomerContainer {
	private String openCode;
	private List<KittyContainer> kitties;

	public CustomerContainer(Customer customer) {
		this.openCode = customer.getOpenCode();
		this.kitties = customer.getKitties().stream().map(x -> new KittyContainer(x))
				.sorted(Comparator.comparing(KittyContainer::getId)).collect(Collectors.toCollection(ArrayList::new));
	}

}
