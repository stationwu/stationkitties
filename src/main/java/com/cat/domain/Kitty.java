package com.cat.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kitty")
public class Kitty {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String kittyName;
	
	private LocalDateTime birthday;

	@OneToOne(cascade={CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE}) 
	@JoinColumn(name="GENE_ID", unique=true, nullable=false, updatable=false)
	private Gene gene;
	
//	private Kitty sire;
//	
//	private Kitty matron;
	
	@OneToMany
	private List<Kitty> children;

	@OneToOne(cascade={CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE}) 
	@JoinColumn(name="IMAGE_ID", unique=true, nullable=false, updatable=false)
	private Image image;

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
	
}
