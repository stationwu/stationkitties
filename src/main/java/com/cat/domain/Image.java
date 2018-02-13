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

    @Column
    private String path;

    @Column
    private String thumbnailPath;

    @Column
    private String smallVersionPath;

    @Column
    @NotNull
    private String contentType;
    
    private String date;
    
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

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public String getSmallVersionPath() {
		return smallVersionPath;
	}

	public void setSmallVersionPath(String smallVersionPath) {
		this.smallVersionPath = smallVersionPath;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Kitty getKitty() {
		return kitty;
	}

	public void setKitty(Kitty kitty) {
		this.kitty = kitty;
	}
    
}
