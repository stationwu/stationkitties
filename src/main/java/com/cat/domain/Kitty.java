package com.cat.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "kitty")
public class Kitty {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String kittyName;
	
	private Gender gender;
	
	private LocalDateTime birthday;
	
	private boolean isForSale = true;

	@OneToOne(cascade={CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE}) 
	@JoinColumn(name="GENE_ID", unique=true, nullable=false, updatable=false)
	private Gene gene;
	
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
    @JsonIgnore
	private Customer customer;
	
	@ManyToOne
	private Kitty sire;
	
	@ManyToOne
	private Kitty matron;
	
	@OneToMany
	private List<Kitty> children;
	
	private BigDecimal price= new BigDecimal(500);

	@OneToOne(cascade={CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE}) 
	@JoinColumn(name="IMAGE_ID", unique=true, nullable=false, updatable=false)
	private Image image;
	
	public Kitty(){
		Random num = new Random();
		if(num.nextBoolean()){
			this.gender = Gender.MALE;
		}else{
			this.gender = Gender.FEMALE;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKittyName() {
		return kittyName;
	}

	public void setKittyName(String kittyName) {
		this.kittyName = kittyName;
	}

	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public LocalDateTime getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDateTime birthday) {
		this.birthday = birthday;
	}

	public List<Kitty> getChildren() {
		return children;
	}

	public void setChildren(List<Kitty> children) {
		this.children = children;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Kitty getSire() {
		return sire;
	}

	public void setSire(Kitty sire) {
		this.sire = sire;
	}

	public Kitty getMatron() {
		return matron;
	}

	public void setMatron(Kitty matron) {
		this.matron = matron;
	}
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public boolean isForSale() {
		return isForSale;
	}

	public void setForSale(boolean isForSale) {
		this.isForSale = isForSale;
	}

	public enum Gender{
		MALE("公"),
		FEMALE("母");
		
		private String gender;
		
		private Gender(String gender){
			this.gender = gender;
		}
		
		@Override
		public String toString() {
			return this.gender;
		}
			
	}
	
	
}
