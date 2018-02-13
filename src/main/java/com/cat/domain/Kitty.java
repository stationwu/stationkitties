package com.cat.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

	@OneToOne
	private Gene gene;
	
	private Kitty sire;
	
	private Kitty matron;
	
	private List<Kitty> children;

	@OneToOne
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

	public String getGene() {
		return gene;
	}

	public void setGene(String gene) {
		this.gene = gene;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
}
