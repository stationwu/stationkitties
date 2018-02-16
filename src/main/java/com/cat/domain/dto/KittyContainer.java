package com.cat.domain.dto;

import java.time.LocalDateTime;
import com.cat.domain.Kitty;

import lombok.Data;

@Data
public class KittyContainer {
	private long id;

	private String kittyName;

	private LocalDateTime birthday;

	private long sireId;

	private long matronId;

	private String gender;

	private String imageUrl;

	public KittyContainer(Kitty kitty) {
		this.id = kitty.getId();
		this.kittyName = kitty.getKittyName();
		this.birthday = kitty.getBirthday();
		this.imageUrl = "/Images/" + kitty.getImage().getId();
		this.gender = kitty.getGender().toString();
		this.sireId = kitty.getSire() == null ? 0 : kitty.getSire().getId();
		this.matronId = kitty.getMatron() == null ? 0 : kitty.getMatron().getId();
	}

}
