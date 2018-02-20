package com.cat.domain.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.cat.domain.Customer;

import lombok.Data;

@Data
public class CustomerContainer {
	private String openCode;
	private BigDecimal wallet;
	private List<KittyContainer> kitties;

	public CustomerContainer(Customer customer) {
		this.openCode = customer.getOpenCode();
		this.wallet = customer.getWallet();
		this.kitties = customer.getKitties().stream().map(x -> new KittyContainer(x))
				.sorted(Comparator.comparing(KittyContainer::getId)).collect(Collectors.toCollection(ArrayList::new));
	}

}
