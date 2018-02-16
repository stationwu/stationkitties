package com.cat.domain;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String openCode;

    private String name;

    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "customer")
    private List<Kitty> kitties = new ArrayList<>();

    public Customer(String openCode){
    	this.openCode = openCode;
    }
    
    public Customer() {
		
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

	public void setKitties(List<Kitty> kitties) {
		this.kitties = kitties;
	}

	public long getId() {
		return id;
	}

	public String getOpenCode() {
		return openCode;
	}
    
}
