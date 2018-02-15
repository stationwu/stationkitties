package com.cat.domain.dto;

import java.time.LocalDateTime;
import com.cat.domain.Kitty;

import lombok.Data;

@Data
public class KittyContainer {
	private long id;

	private String kittyName;

	private LocalDateTime birthday;

	// private Kitty sire;
	//
	// private Kitty matron;

	private String imageUrl;

	public KittyContainer(Kitty kitty) {
		this.id = kitty.getId();
		this.kittyName = kitty.getKittyName();
		this.birthday = kitty.getBirthday();
		this.imageUrl = "/Images/" + kitty.getImage().getId();
	}

}
