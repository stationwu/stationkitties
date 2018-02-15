package com.cat.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author I067768
 *
 */
@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String imageName;

    private String path;
    
    private String contentType;

    @OneToOne
    private Kitty kitty;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Kitty getKitty() {
		return kitty;
	}

	public void setKitty(Kitty kitty) {
		this.kitty = kitty;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
}
