package com.cat.domain;


import javax.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique=true,nullable=false)
    private String openCode;

    private String name;
    
    private BigDecimal wallet = new BigDecimal(1000);

    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "customer", fetch=FetchType.EAGER)
    private List<Kitty> kitties = new ArrayList<>();

    public Customer(String openCode){
    	this.openCode = openCode;
    }
    
    public Customer() {
    	this.wallet = new BigDecimal(1000);
	}
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Kitty> getKitties() {
		return kitties;
	}
	
	public void addKitties(Kitty kitty) {
		this.kitties.add(kitty);
	}
	
	public void removeKitties(Kitty kitty) {
		this.kitties.remove(kitty);
	}

	public void setKitties(List<Kitty> kitties) {
		this.kitties = kitties;
	}

	public long getId() {
		return id;
	}

	public String getOpenCode() {
		return openCode;
	}

	public BigDecimal getWallet() {
		return wallet;
	}

	public void setWallet(BigDecimal wallet) {
		this.wallet = wallet;
	}
	
}
